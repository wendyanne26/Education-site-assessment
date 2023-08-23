package com.chinwendu.studentservice.repositories;

import com.chinwendu.studentservice.model.Course;
import com.chinwendu.studentservice.model.Student;
import com.chinwendu.studentservice.model.StudentCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentCourseRepository extends JpaRepository<StudentCourses, Long> {
    @Query("SELECT c.course FROM StudentCourses c WHERE c.id = ?1 AND c.course.id = ?2 ")
    Optional<Course> findCourseByStudentIdAndCourseId(Long id, Long courseId);
    @Query("SELECT c.student FROM StudentCourses c WHERE  c.course.id = ?1 ")
    Optional<Student> findStudentByCourseId(Long id);
}