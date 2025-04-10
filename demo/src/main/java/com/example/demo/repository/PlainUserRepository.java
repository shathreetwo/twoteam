package com.example.demo.repository;

import com.example.demo.domain.PlainUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlainUserRepository extends JpaRepository<PlainUser, Long> {
    boolean existsByUsername(String username);
}