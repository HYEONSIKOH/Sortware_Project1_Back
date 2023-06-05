package com.example.sw.Api;

import com.example.sw.Flask.MovieRecommender;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RequestMapping("search")
@RestController
public class recommand_movie {

    MovieRecommender movieRecommender;

    public recommand_movie(MovieRecommender movieRecommender) {
        this.movieRecommender = movieRecommender;
    }

    @GetMapping("recommand")
    @ResponseBody
    public String RecomnmandMovie(@RequestParam("Movieid") String id) {
        return movieRecommender.Recommend(id);
    }

    // 협업필터링 -> 로그인 버전
    @PostMapping("collaborative")
    public String Cf(Authentication authentication) {
        long userId = Long.parseLong(authentication.getName());

        return movieRecommender.Collaborative_Filtering(userId);
    }

    @GetMapping("updateData")
    public ResponseEntity<String> Cf() {
        movieRecommender.run_database_query();

        return ResponseEntity.ok().body("업데이트 완료");
    }
}