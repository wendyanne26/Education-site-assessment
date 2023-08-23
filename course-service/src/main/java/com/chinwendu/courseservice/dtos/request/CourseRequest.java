package com.chinwendu.courseservice.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class CourseRequest {
    private String courseCode;
    private String courseTitle;
    private String courseDescription;
}
