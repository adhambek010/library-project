package com.example.library.weblibrary.repositories;

import com.example.library.weblibrary.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<User, Integer> {
}
