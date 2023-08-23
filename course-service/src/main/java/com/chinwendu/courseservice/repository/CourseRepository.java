package com.chinwendu.courseservice.repository;

import com.chinwendu.courseservice.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findAllById(Long id);
}
