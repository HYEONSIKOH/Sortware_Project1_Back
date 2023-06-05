package com.example.sw.Api;


import com.example.sw.Jwt.Util;
import com.example.sw.domain.Board;
import com.example.sw.dto.BoardForm;
import com.example.sw.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/board")
public class BoardApi {
    @Value("${jwt.secret}")
    private String secretKey;

    private BoardRepository boardRepository;

    private Util util;

    public BoardApi(BoardRepository boardRepository, Util util) {
        this.boardRepository = boardRepository;
        this.util = util;
    }
    @PostMapping("insert")
    public ResponseEntity<String> SaveBoard (Authentication authentication, @RequestBody BoardForm form) {
        // 토큰을 이용하여 userId 값을 가져옴
        String userId = authentication.getName();
        System.out.println(userId);

        System.out.println(userId);
        form.setUserid(Long.parseLong(userId));

        // 1. Dto를 Entity 변환
        Board board = form.toEntity();
        System.out.println(board.toString());

        // 2. Repository에게 Entity를 DB로 저장하게 함
        Board saved = boardRepository.save(board);
        System.out.println(saved.toString());

        return ResponseEntity.ok().body("평점 감사합니다ㅏ~~");
    }

    @GetMapping("return")
    @ResponseBody
    public List<Board> ReturnBoard (@RequestParam("movieid") long movieid) {

        System.out.println("movieId = " + movieid);
        return boardRepository.findAllBymovieid(movieid);
        // return boardList;
    }
}
