package com.example.sw.Flask;

import com.example.sw.domain.Board;
import com.example.sw.repository.BoardRepository;
import com.example.sw.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MovieRecommender {
    private BoardRepository boardRepository;
    public MovieRecommender(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public String Recommend(String id) {
        final String uri = "http://127.0.0.1:5000/recommend"; // Flask 서버의 API URL
        RestTemplate restTemplate = new RestTemplate();

        // Flask 서버에 POST 요청 보내기
        Map<String, String> params = new HashMap<>();
        params.put("id", id); // 추천을 받을 영화 제목
        String result = restTemplate.postForObject(uri, params, String.class);
        String decodedResult = StringEscapeUtils.unescapeJava(result);

        System.out.println("MovieId: " + decodedResult);
        return decodedResult;
    }

    public String Collaborative_Filtering(long userid) {
        final String uri = "http://127.0.0.1:5000/collaborative"; // Flask 서버의 API URL
        RestTemplate restTemplate = new RestTemplate();

        // Flask 서버에 POST 요청 보내기
        Map<String, Long> params = new HashMap<>();
        params.put("userid", userid); // 추천을 받을 영화 제목
        String result = restTemplate.postForObject(uri, params, String.class);
        String response = StringEscapeUtils.unescapeJava(result);

        System.out.println("MovieId: " + response);
        return response;
    }

    public ResponseEntity<String> run_database_query() {
        List<Board> boardList = boardRepository.findAll();
        List<Map<String, Object>> strippedList = new ArrayList<>();

        Map<String, Object> strippedBoard = new HashMap<>();

        strippedBoard.put("rating", 0);
        strippedBoard.put("movieid", 0);
        strippedBoard.put("userid", 0);

        strippedList.add(strippedBoard);

        // DB에서 가져온 값을 json으로 바꿔주는 코드
        for (Board board : boardList) {
            // userid, movieid, rating만 포함하는 맵 생성
            strippedBoard = new HashMap<>();

            strippedBoard.put("rating", board.getRating());
            strippedBoard.put("movieid", board.getMovieid());
            strippedBoard.put("userid", board.getUserid());

            strippedList.add(strippedBoard);
        }

        String strippedListJson;
        try {
            // strippedList를 JSON 형태로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            strippedListJson = objectMapper.writeValueAsString(strippedList);

            // System.out.println("Stripped List JSON: " + strippedListJson);
        } catch (JsonProcessingException e) {
            // 예외 처리 코드
            e.printStackTrace();
            // 예외 발생 시 반환할 값을 지정하거나 예외를 다시 던지는 등의 처리를 수행할 수 있습니다.
            strippedListJson = null;
        }

        final String uri = "http://127.0.0.1:5000/main"; // Flask 서버의 API URL
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(strippedListJson, headers);

        String response;
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
            response = responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            response = "플라스크 서버가 열리지 않은 상태입니다.";
        }


        System.out.println("Response: " + response);
        return ResponseEntity.ok().build();
    }
}
