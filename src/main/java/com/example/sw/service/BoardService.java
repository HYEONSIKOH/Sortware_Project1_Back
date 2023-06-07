package com.example.sw.service;

import com.example.sw.domain.Board;
import com.example.sw.domain.User;
import com.example.sw.repository.BoardRepository;
import com.example.sw.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BoardService {

    private BoardRepository boardRepository;
    private UserRepository userRepository;

    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public String BoardLoader(long movieid) {
        List<Board> boardList = boardRepository.findAllBymovieid(movieid);
        List<Map<String, Object>> strippedList = new ArrayList<>();

        // DB에서 가져온 값을 json으로 바꿔주는 코드
        for (Board board : boardList) {
            Map<String, Object> strippedBoard = new HashMap<>();
            Optional<String> optionalNickname = userRepository.findNicknameByUserId(board.getUserid()); // DB에서 닉네임 찾아오기

            String nickName = optionalNickname.orElse("");

            String dateString = "";
            if (board.getDate() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm");
                dateString = board.getDate().format(formatter);
            }

            strippedBoard.put("id", board.getUserid());
            strippedBoard.put("rating", board.getRating());
            strippedBoard.put("nickname", nickName);
            strippedBoard.put("date", dateString);
            strippedBoard.put("comment", board.getComment());

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

        return strippedListJson;
    }
}
