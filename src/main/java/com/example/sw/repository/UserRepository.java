package com.example.sw.repository;

import com.example.sw.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(String id);
    Optional<String> findByEmail(String Email);
    @Query(value = "SELECT nickname FROM user u WHERE u.id = :id", nativeQuery = true)
    Optional<String> findNicknameById(@Param("id") String id);

    @Query(value = "SELECT nickname FROM user u WHERE u.userid = :userid", nativeQuery = true)
    Optional<String> findNicknameByUserId(@Param("userid") long userid);

}
