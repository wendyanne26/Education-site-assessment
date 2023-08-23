package com.chinwendu.courseservice.controller;

import com.chinwendu.courseservice.dtos.request.CourseRequest;
import com.chinwendu.courseservice.exceptions.CustomException;
import com.chinwendu.courseservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/course")
public class CourseController {
    private final CourseService courseService;
    @PostMapping("/addCourses")
    public ResponseEntity<?> addCourse(@RequestBody CourseRequest courseRequest) throws CustomException {
        return new ResponseEntity<>(courseService.addCourse(courseRequest), HttpStatus.CREATED);
    }

    @GetMapping("/getCourses")
    public ResponseEntity<?> getCourses() throws CustomException {
        return new ResponseEntity<>(courseService.getCourses(), HttpStatus.OK);
    }
    @GetMapping("/getCourse/{id}")
    public ResponseEntity<?> getCourse(@PathVariable Long id) throws CustomException {
        return new ResponseEntity<>(courseService.getCourse(id), HttpStatus.OK);
    }

    @PutMapping("/updateCourse/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody CourseRequest request) throws CustomException {
        return new ResponseEntity<>(courseService.updateCourse(id, request), HttpStatus.OK);
    }
    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) throws CustomException {
        return new ResponseEntity<>(courseService.deleteCourse(id), HttpStatus.OK);
    }
}
