package com.chinwendu.studentservice.controllers;

import com.chinwendu.studentservice.dtos.request.RegistrationRequest;
import com.chinwendu.studentservice.dtos.request.UpdateCourseByStudentRequest;
import com.chinwendu.studentservice.exceptions.CustomException;
import com.chinwendu.studentservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService service;

    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@RequestBody RegistrationRequest request) throws CustomException {
        return new ResponseEntity<>(service.registerStudent(request), HttpStatus.CREATED);
    }

    @GetMapping("/getListOfCourses")
    public ResponseEntity<?> getListOfCourses() throws CustomException {
        System.out.println("<<<<<<<<<<<<<<got here>>>>>>>>>>>>>>>>>>>>>>");
        return new ResponseEntity<>(service.getListOfCourses(), HttpStatus.FOUND);
    }

    @GetMapping("/choose-course/{id}")
    public ResponseEntity<?> allocateCourse(@PathVariable Long id) throws CustomException {
        return new ResponseEntity<>(service.allocateCourses(id), HttpStatus.OK);
    }

    @PutMapping("/update-course/{courseId}")
    public ResponseEntity<?> updateCourseByStudent(@RequestBody UpdateCourseByStudentRequest updateCourseByStudentRequest, @PathVariable Long courseId) throws CustomException {
        return new ResponseEntity<>(service.updateCourseByStudent(updateCourseByStudentRequest, courseId), HttpStatus.OK);
    }
    @GetMapping("/get-all-students-by-course/{id}")
    public ResponseEntity<?> getAllStudentByCourse(@PathVariable Long id){
        return new ResponseEntity<>(service.getAllStudentsByCourse(id), HttpStatus.FOUND);
    }
    @DeleteMapping("/delete-student/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Long id) throws CustomException {
        return new ResponseEntity<>(service.deleteStudentById(id), HttpStatus.OK);
    }
}
