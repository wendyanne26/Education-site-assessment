package com.chinwendu.studentservice.service;

import com.chinwendu.studentservice.dtos.response.CourseListResponse;
import com.chinwendu.studentservice.dtos.response.CourseResponse;
import com.chinwendu.studentservice.exceptions.CustomException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "${course-client-api.name}", url = "${course-client-api.base-url}")
public interface CoursesClient {
    @GetMapping("/getCourses")
     ResponseEntity<CourseListResponse> getCourses() throws CustomException;

    @GetMapping("/getCourse/{id}")
    ResponseEntity<CourseResponse> getCourse(@PathVariable Long id);
}
