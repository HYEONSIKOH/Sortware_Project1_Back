package com.example.sw.repository;

import com.example.sw.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(String id);
    Optional<User> findByEmail(String id);
    Optional<User> findByNickname(String id);

}
