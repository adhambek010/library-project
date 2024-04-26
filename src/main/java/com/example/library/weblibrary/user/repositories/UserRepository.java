package com.example.library.weblibrary.user.repositories;

import com.example.library.weblibrary.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<User, Integer> {
}
