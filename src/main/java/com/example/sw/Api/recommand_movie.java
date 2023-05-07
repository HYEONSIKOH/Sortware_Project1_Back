package com.example.sw.Api;

import com.example.sw.Flask.MovieRecommender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RequestMapping("search")
@Controller
public class recommand_movie {

    MovieRecommender movieRecommender = new MovieRecommender();

    @GetMapping("recommand")
    @ResponseBody
    public String helloString(@RequestParam("Movieid") String id) {
        System.out.println(id);

        String testcode = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]";

        // return movieRecommender.Recommend(id);
        return testcode;
    }
}


