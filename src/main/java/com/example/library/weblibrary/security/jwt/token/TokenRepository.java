package com.example.library.weblibrary.security.jwt.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {
    @Query(
            value = """
                SELECT t FROM Token AS t
                INNER JOIN User AS u
                ON t.identifier = u.identifier
                WHERE u.identifier = t.identifier AND (t.expired = FALSE OR t.revoked = FALSE)
            """
    )
    List<Token> findAllValidTokenByUser(String id);
    Optional<Token> findByToken(String token);
}
