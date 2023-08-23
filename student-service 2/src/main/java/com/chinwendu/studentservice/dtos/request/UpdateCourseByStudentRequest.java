package com.chinwendu.studentservice.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCourseByStudentRequest {
    private String courseCode;
    private String courseTitle;
    private String courseDescription;
}
