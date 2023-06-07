package com.example.sw.Api;

import com.example.sw.domain.Board;
import com.example.sw.dto.BoardForm;
import com.example.sw.repository.BoardRepository;
import com.example.sw.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://121.165.182.97:3000")
@RestController
@RequestMapping("/board")
public class BoardApi {

    private BoardRepository boardRepository;
    private BoardService boardService;

    public BoardApi(BoardRepository boardRepository, BoardService boardService) {
        this.boardRepository = boardRepository;
        this.boardService = boardService;
    }

    @PostMapping("insert")
    public ResponseEntity<String> SaveBoard (Authentication authentication, @RequestBody BoardForm form) {
        // 토큰을 이용하여 userId 값을 가져옴
        String userId = authentication.getName();
        form.setUserid(Long.parseLong(userId));

        // 1. Dto를 Entity 변환
        Board board = form.toEntity();

        // 2. Repository에게 Entity를 DB로 저장하게 함
        Board saved = boardRepository.save(board);

        return ResponseEntity.ok().body("평점 감사합니다ㅏ~~");
    }

    @GetMapping("return")
    @ResponseBody
    public String ReturnBoard (@RequestParam("movieid") long movieid) {
        String boardData = boardService.BoardLoader(movieid);

        return boardData;
    }
}
