package com.chinwendu.courseservice.dtos.response;

import com.chinwendu.courseservice.model.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class CourseResponse {
    private String courseCode;
    private String courseTitle;
    private String courseDescription;

    public CourseResponse(Course course) {
        this.courseCode = course.getCourseCode();
        this.courseTitle = course.getCourseTitle();
        this.courseDescription = course.getCourseDescription();
    }
}
