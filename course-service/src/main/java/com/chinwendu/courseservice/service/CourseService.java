package com.chinwendu.courseservice.service;

import com.chinwendu.courseservice.dtos.request.CourseRequest;
import com.chinwendu.courseservice.dtos.response.GenericResponse;
import com.chinwendu.courseservice.exceptions.CustomException;

public interface CourseService {
    GenericResponse addCourse(CourseRequest courseRequest) throws CustomException;

    GenericResponse getCourses() throws CustomException;

    GenericResponse updateCourse(Long id, CourseRequest request) throws CustomException;

    GenericResponse deleteCourse(Long id) throws CustomException;

    GenericResponse getCourse(Long id) throws CustomException;
}

