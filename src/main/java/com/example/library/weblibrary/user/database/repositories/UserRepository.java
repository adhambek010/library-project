package com.example.library.weblibrary.user.database.repositories;

import com.example.library.weblibrary.user.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String email);
}
