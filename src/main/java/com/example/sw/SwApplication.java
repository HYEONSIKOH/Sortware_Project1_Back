package com.example.sw;

import com.example.sw.Flask.MovieRecommender;
import com.example.sw.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwApplication {
	private static MovieRecommender movieRecommender;

	@Autowired
	public SwApplication(BoardRepository boardRepository) {
		this.movieRecommender = new MovieRecommender(boardRepository);
	}

	public static void main(String[] args) {
		SpringApplication.run(SwApplication.class, args);

		movieRecommender.run_database_query();
	}

}
