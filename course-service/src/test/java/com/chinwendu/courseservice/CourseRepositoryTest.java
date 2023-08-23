package com.chinwendu.courseservice;

import com.chinwendu.courseservice.model.Course;
import com.chinwendu.courseservice.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void save() {
        Course course = new Course();

        course.setCourseTitle("Bio");
        course.setCourseDescription("Important");
        course.setCourseCode("BIO201");


        Course savedCourse = courseRepository.save(course);

        assertNotNull(savedCourse);
        assertEquals(savedCourse.getCourseCode(), course.getCourseCode());
    }

    @Test
    void getAllCourses() {
        Course course = new Course();

        course.setCourseTitle("Bio");
        course.setCourseDescription("Important");
        course.setCourseCode("BIO201");

        courseRepository.save(course);


        List<Course> list = courseRepository.findAll();

        assertNotNull(list);
        assertEquals(1, list.size());
    }

    @Test
    void getCourseById() {
        Course course = new Course();
        course.setCourseTitle("Bio");
        course.setCourseDescription("Important");
        course.setCourseCode("BIO201");
        Course savedCourse = courseRepository.save(course);

        courseRepository.findById(savedCourse.getId());


        assertNotNull(savedCourse);
    }
}
