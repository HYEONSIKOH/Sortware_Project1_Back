package com.example.sw.Flask;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class MovieRecommender {
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
}
