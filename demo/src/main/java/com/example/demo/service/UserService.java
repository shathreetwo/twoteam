package com.example.demo.service;

import com.example.demo.domain.PlainUser;
import com.example.demo.domain.User;
import com.example.demo.repository.PlainUserRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// service/UserService.java
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlainUserRepository plainUserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 사용자 등록 메소드
    public boolean registerUser(User user) {
        // 아이디 중복 검사
        if (userRepository.existsByUsername(user.getUsername())) {
            return false; // 중복된 아이디가 있으면 false 반환
        }

        // 1. PlainUser에 평문 비밀번호 저장
        PlainUser plainUser = new PlainUser(
                user.getUsername(),
                user.getPassword(),  // 암호화 되지 않은 비밀번호 그대로 저장
                user.getNickname(),
                user.getEmail()
        );

        plainUserRepository.save(plainUser);// 평문 비밀번호 저장

        // 2. User에 암호화된 비밀번호 저장
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // 비밀번호 암호화
        userRepository.save(user);  // 암호화된 비밀번호 저장

        return true;  // 회원가입 성공
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())  // 암호화된 비밀번호
                .roles("USER") // 권한
                .build();
    }

}
