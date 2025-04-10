package com.example.demo.repository;


import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 사용자명으로 사용자 검색
    User findByUsername(String username);

    boolean existsByUsername(String username);
}