package com.chinwendu.studentservice.repositories;

import com.chinwendu.studentservice.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
