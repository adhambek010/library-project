package com.example.library.weblibrary.user.services;

import com.example.library.weblibrary.user.database.entities.UserEntity;
import com.example.library.weblibrary.config.exception.UserNotFoundException;
import com.example.library.weblibrary.user.database.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository studentRepository;

    /**
     * Retrieves all students from the repository.
     *
     * @return A list of all students.
     */
    public List<UserEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Retrieves a student by their ID from the repository.
     *
     * @param id The ID of the student to retrieve.
     * @return The student with the specified ID.
     * @throws UserNotFoundException If no student with the specified ID is found.
     */
    public UserEntity getStudent(String  id) {
        Optional<UserEntity> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new UserNotFoundException("Student not found with id " + id);
        }
    }

    /**
     * Adds a student to the repository.
     *
     * @param student The student to add.
     * @return The added student.
     */
    public UserEntity addStudent(UserEntity student) {
        return studentRepository.save(student);
    }

    /**
     * Deletes a student by their ID from the repository.
     *
     * @param id The ID of the student to delete.
     * @throws UserNotFoundException If no student with the specified ID is found.
     */
    public void deleteStudent(String id) {
        Optional<UserEntity> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.delete(student.get());
        } else {
            throw new UserNotFoundException("Student not found with id " + id);
        }
    }


}
