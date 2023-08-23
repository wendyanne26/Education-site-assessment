package com.chinwendu.studentservice.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private boolean status;
    private String message;
    private CourseDto data;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CourseDto{
        private String courseCode;
        private String courseTitle;
        private String courseDescription;
    }
}
