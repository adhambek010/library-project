package com.example.library.weblibrary.user.services;

import com.example.library.weblibrary.user.entities.User;
import com.example.library.weblibrary.exception.UserNotFoundException;
import com.example.library.weblibrary.user.repositories.UserRepository;
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
    public List<User> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Retrieves a student by their ID from the repository.
     *
     * @param id The ID of the student to retrieve.
     * @return The student with the specified ID.
     * @throws UserNotFoundException If no student with the specified ID is found.
     */
    public User getStudent(int id) {
        Optional<User> student = studentRepository.findById(id);
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
    public User addStudent(User student) {
        return studentRepository.save(student);
    }

    /**
     * Deletes a student by their ID from the repository.
     *
     * @param id The ID of the student to delete.
     * @throws UserNotFoundException If no student with the specified ID is found.
     */
    public void deleteStudent(int id) {
        Optional<User> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.delete(student.get());
        } else {
            throw new UserNotFoundException("Student not found with id " + id);
        }
    }


}
