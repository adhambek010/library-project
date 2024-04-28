//package com.example.library.weblibrary.config.disable.token;
//
//import com.example.library.weblibrary.user.database.entities.UserEntity;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class Token {
//    @Id
//    @GeneratedValue
//    private int id;
//    @Column(unique = true)
//    private String token;
//    private TokenType tokenType = TokenType.BEARER;
//    private boolean revoked;
//    private boolean expired;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private UserEntity userEntity;
//}
