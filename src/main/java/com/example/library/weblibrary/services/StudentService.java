package com.example.library.weblibrary.services;

import com.example.library.weblibrary.entities.Student;
import com.example.library.weblibrary.exception.StudentNotFoundException;
import com.example.library.weblibrary.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    /**
     * Retrieves all students from the repository.
     *
     * @return A list of all students.
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Retrieves a student by their ID from the repository.
     *
     * @param id The ID of the student to retrieve.
     * @return The student with the specified ID.
     * @throws StudentNotFoundException If no student with the specified ID is found.
     */
    public Student getStudent(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new StudentNotFoundException("Student not found with id " + id);
        }
    }

    /**
     * Adds a student to the repository.
     *
     * @param student The student to add.
     * @return The added student.
     */
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    /**
     * Deletes a student by their ID from the repository.
     *
     * @param id The ID of the student to delete.
     * @throws StudentNotFoundException If no student with the specified ID is found.
     */
    public void deleteStudent(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.delete(student.get());
        } else {
            throw new StudentNotFoundException("Student not found with id " + id);
        }
    }

}