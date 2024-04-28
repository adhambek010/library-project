package com.example.library.weblibrary.config.disable.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query(
            value = """
                SELECT t FROM Token AS t
                INNER JOIN User AS u
                ON t.id = u.id
                WHERE u.id = t.id AND (t.expired = FALSE OR t.revoked = FALSE)
            """
    )
    List<Token> findAllValidTokenByUser(Integer id);
    Optional<Token> findByToken(String token);
}
