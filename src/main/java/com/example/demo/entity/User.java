package com.example.demo.entity;

import com.example.demo.constant.RoleType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;

    private RoleType type;

    @Builder
    public User(String email, String password, RoleType type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }
}
