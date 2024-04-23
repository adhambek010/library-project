package com.example.library.weblibrary.controllers;

import com.example.library.weblibrary.entities.Student;
import com.example.library.weblibrary.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.library.weblibrary.endpoints.Endpoints.*;

@RestController
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    /**
     * Retrieves all students.
     *
     * @return A list of all students.
     */
    @GetMapping(GET_ALL_STUDENTS)
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    /**
     * Retrieves a student by ID.
     *
     * @param id The ID of the student to retrieve.
     * @return The student with the specified ID.
     */
    @GetMapping(GET_STUDENT)
    public Student getStudent(@PathVariable int id) {
        return studentService.getStudent(id);
    }

    /**
     * Adds a student.
     *
     * @param student The student to add.
     * @return The added student.
     */
    @PutMapping(ADD_STUDENT)
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    //TODO: update student through patchMapping or putMapping

    /**
     * Deletes a student by ID.
     *
     * @param id The ID of the student to delete.
     */
    @DeleteMapping(DELETE_STUDENT)
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }

}
