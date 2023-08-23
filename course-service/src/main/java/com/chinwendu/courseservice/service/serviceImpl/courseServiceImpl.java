package com.chinwendu.courseservice.service.serviceImpl;

import com.chinwendu.courseservice.dtos.request.CourseRequest;
import com.chinwendu.courseservice.dtos.response.GenericResponse;
import com.chinwendu.courseservice.dtos.response.CourseResponse;
import com.chinwendu.courseservice.exceptions.CustomException;
import com.chinwendu.courseservice.model.Course;
import com.chinwendu.courseservice.repository.CourseRepository;
import com.chinwendu.courseservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class courseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Override
    public GenericResponse addCourse(CourseRequest courseRequest) throws CustomException {
        Course course = new Course();
        course.setCourseCode(courseRequest.getCourseCode());
        course.setCourseTitle(courseRequest.getCourseTitle());
        course.setCourseDescription(courseRequest.getCourseDescription());
        try {
            courseRepository.save(course);
        }catch (Exception e){
            throw new CustomException("save operation failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        CourseResponse response = new CourseResponse(course);
        return new GenericResponse(true, "success", response);
    }

    @Override
    public GenericResponse getCourses() throws CustomException {
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()) {
            throw new CustomException("no courses found", HttpStatus.NOT_FOUND);
        }
        List<CourseResponse> response = courses
                .stream()
                .map(CourseResponse::new)
                .toList();

        return new GenericResponse(true, "success", response);
    }

    @Override
    public GenericResponse updateCourse(Long id, CourseRequest request) throws CustomException {
        Course course = courseRepository.findById(id)
                .orElseThrow(()-> new CustomException("course not found", HttpStatus.NOT_FOUND));
        course.setCourseDescription(request.getCourseDescription());
        course.setCourseDescription(request.getCourseDescription());
        course.setCourseTitle(request.getCourseTitle());
        try {
            courseRepository.save(course);
        }catch (Exception e){
            throw new CustomException("save operation failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        CourseResponse response = new CourseResponse(course);
        return new GenericResponse(true, "success", response);
    }

    @Override
    public GenericResponse deleteCourse(Long id) throws CustomException {
        Course course = courseRepository.findById(id)
                .orElseThrow(()-> new CustomException("course not found", HttpStatus.NOT_FOUND));
        try {
            courseRepository.delete(course);
        }catch (Exception e){
            throw new CustomException("course deletion failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new GenericResponse(true, "deleted");

    }

    @Override
    public GenericResponse getCourse(Long id) throws CustomException {
        Course course = courseRepository.findById(id)
                .orElseThrow(()-> new CustomException("course not found", HttpStatus.NOT_FOUND));
        CourseResponse response = new CourseResponse(course);
        return new GenericResponse(true, "success", response);
    }


}