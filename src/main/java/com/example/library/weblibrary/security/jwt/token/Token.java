package com.example.library.weblibrary.security.jwt.token;

import com.example.library.weblibrary.user.entities.BaseEntity;
import com.example.library.weblibrary.user.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token extends BaseEntity {
    @Column(unique = true)
    private String token;
    private TokenType tokenType = TokenType.BEARER;
    private boolean revoked;
    private boolean expired;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_identifier")
    private User user;
}
