package com.example.sw.repository;

import com.example.sw.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllBymovieid(long movieid);
    List<Board> findAll();
}
