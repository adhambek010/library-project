package com.example.library.weblibrary.user.controllers;

import com.example.library.weblibrary.user.database.entities.UserEntity;
import com.example.library.weblibrary.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.library.weblibrary.config.endpoints.Endpoints.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService studentService;

    /**
     * Retrieves all students.
     *
     * @return A list of all students.
     */
    @GetMapping(GET_ALL_STUDENTS)
    public List<UserEntity> getAllStudents() {
        return studentService.getAllStudents();
    }

    /**
     * Retrieves a student by ID.
     *
     * @param id The ID of the student to retrieve.
     * @return The student with the specified ID.
     */
    @GetMapping(GET_STUDENT)
    public UserEntity getStudent(@PathVariable String  id) {
        return studentService.getStudent(id);
    }

    /**
     * Adds a student.
     *
     * @param student The student to add.
     * @return The added student.
     */
    @PutMapping(ADD_STUDENT)
    public UserEntity addStudent(@RequestBody UserEntity student) {
        return studentService.addStudent(student);
    }

    //TODO: update student through patchMapping or putMapping

    /**
     * Deletes a student by ID.
     *
     * @param id The ID of the student to delete.
     */
    @DeleteMapping(DELETE_STUDENT)
    public void deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
    }

    @PostMapping("/user/login")
    public Authentication login(Authentication authentication) {
        return authentication;
    }

}
