package com.example.library.weblibrary.repositories;

import com.example.library.weblibrary.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
