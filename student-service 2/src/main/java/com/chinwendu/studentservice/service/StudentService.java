package com.chinwendu.studentservice.service;

import com.chinwendu.studentservice.dtos.request.RegistrationRequest;
import com.chinwendu.studentservice.dtos.request.UpdateCourseByStudentRequest;
import com.chinwendu.studentservice.dtos.response.CourseListResponse;
import com.chinwendu.studentservice.dtos.response.GenericResponse;
import com.chinwendu.studentservice.exceptions.CustomException;

public interface StudentService {
    GenericResponse registerStudent(RegistrationRequest request) throws CustomException;


   CourseListResponse getListOfCourses() throws CustomException;

    GenericResponse allocateCourses(Long id) throws CustomException;

    GenericResponse getAllStudentsByCourse(Long id) ;

    GenericResponse updateCourseByStudent(UpdateCourseByStudentRequest updateCourseByStudentRequest, Long courseId) throws CustomException;

    GenericResponse deleteStudentById(Long id) throws CustomException;
}
