// domain/PlainUser.java
package com.example.demo.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "users_plain") // 평문 비밀번호 테이블
public class PlainUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String nickname;
    private String email;

    public PlainUser() {}

    public PlainUser(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    // getter/setter 생략 가능
}
