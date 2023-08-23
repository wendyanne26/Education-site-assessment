package com.chinwendu.studentservice.repositories;

import com.chinwendu.studentservice.model.Course;
import com.chinwendu.studentservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByEmailIgnoreCase(String username);

}
