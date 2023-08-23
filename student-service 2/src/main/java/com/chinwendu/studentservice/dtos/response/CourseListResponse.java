package com.chinwendu.studentservice.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseListResponse {
    private boolean status;
    private String message;
    private List<?> data;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CourseResponse{
        private String courseCode;
        private String courseTitle;
        private String courseDescription;
    }
}
