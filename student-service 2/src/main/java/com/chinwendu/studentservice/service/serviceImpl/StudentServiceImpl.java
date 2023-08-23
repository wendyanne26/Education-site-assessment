package com.chinwendu.studentservice.service.serviceImpl;

import com.chinwendu.studentservice.config.JwtUtilsConfig;
import com.chinwendu.studentservice.config.SecurityUtils;
import com.chinwendu.studentservice.dtos.request.RegistrationRequest;
import com.chinwendu.studentservice.dtos.request.UpdateCourseByStudentRequest;
import com.chinwendu.studentservice.dtos.response.*;
import com.chinwendu.studentservice.exceptions.CustomException;
import com.chinwendu.studentservice.model.Course;
import com.chinwendu.studentservice.model.Student;
import com.chinwendu.studentservice.model.StudentCourses;
import com.chinwendu.studentservice.repositories.CourseRepository;
import com.chinwendu.studentservice.repositories.StudentCourseRepository;
import com.chinwendu.studentservice.repositories.StudentRepository;
import com.chinwendu.studentservice.service.CoursesClient;
import com.chinwendu.studentservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;
    private final CoursesClient client;
    private final SecurityUtils securityUtils;
    private final CourseRepository courseRepository;
    private final StudentCourseRepository studentCourseRepository;
    private final JwtUtilsConfig jwtUtilsConfig;
    @Override
    public GenericResponse registerStudent(RegistrationRequest request) throws CustomException {
        Student student = new Student();
        student.setAddress(request.getAddress());
        student.setEmail(request.getEmail());
        student.setArabicName(request.getArabicName());
        student.setEnglishName(request.getEnglishName());
        student.setTelephoneNumber(request.getTelephoneNumber());
        student.setAddress(request.getAddress());
        try {
            repository.save(student);
        } catch (Exception e) {
            throw new CustomException("save operation failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String token = jwtUtilsConfig.generateToken(student);
        RegistrationResponse response = new RegistrationResponse(student);
        response.setToken(token);
        return new GenericResponse(true, "success", response);
    }
    @Override
    public CourseListResponse getListOfCourses() throws CustomException {
        // todo Use ur FeignClient to call the course service
        ResponseEntity<CourseListResponse> response = client.getCourses();
        CourseListResponse courseListRes;
        if (response.getStatusCode().is2xxSuccessful()) {
           courseListRes = response.getBody();
        } else {
            throw new CustomException("Failed to retrieve courses.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return courseListRes;
    }
    private CourseResponse getCourseById(Long id) throws CustomException {
        ResponseEntity<CourseResponse> response = client.getCourse(id);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new CustomException("Failed to retrieve courses.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public GenericResponse allocateCourses(Long id) throws CustomException {
            Student student = getStudent();
            StudentCourses courses = new StudentCourses();
            CourseResponse response;
            try {
                response = getCourseById(id);
            }catch (Exception e){
                throw new CustomException("Error while parsing data", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(!Objects.isNull(response)) {
                Course course = new Course();
                course.setCourseTitle(response.getData().getCourseTitle());
                course.setCourseDescription(response.getData().getCourseDescription());
                course.setCourseCode(response.getData().getCourseCode());

               Course savedCourse = courseRepository.save(course);


               repository.save(student);
               courses.setStudent(student);
               courses.setCourse(savedCourse);
               studentCourseRepository.save(courses);

                return new GenericResponse(true, "success", savedCourse);
            }
            throw new CustomException("error occurred while parsing", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public GenericResponse getAllStudentsByCourse(Long id) {
        List<Student> students = studentCourseRepository.findStudentByCourseId(id)
                .stream().toList();
        List<StudentResponse> response = students.stream().map(StudentResponse::new).toList();
        return new GenericResponse(true, "success", response);
    }

    @Override
    public GenericResponse updateCourseByStudent(UpdateCourseByStudentRequest updateCourseByStudentRequest, Long courseId) throws CustomException {
        Student student = getStudent();
        Course course = studentCourseRepository.findCourseByStudentIdAndCourseId(student.getId(), courseId)
                .orElseThrow(() -> new CustomException("error occurred while parsing", HttpStatus.INTERNAL_SERVER_ERROR));

        course.setCourseCode(updateCourseByStudentRequest.getCourseCode());
        course.setCourseTitle(updateCourseByStudentRequest.getCourseTitle());
        course.setCourseDescription(updateCourseByStudentRequest.getCourseDescription());
        StudentCourses studentCourses = new StudentCourses();
        studentCourses.setCourse(course);
        studentCourseRepository.save(studentCourses);

        return new GenericResponse(true, "successful", studentCourses );
    }

    @Override
    public GenericResponse deleteStudentById(Long id) throws CustomException {
            Student student = getStudent();
            try{
                repository.delete(student);
                return new GenericResponse(true, "success", "student deleted");
            } catch (Exception e){
                throw  new CustomException("Error deleting student", HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    private Student getStudent() throws CustomException {
        String email = securityUtils.getCurrentUserDetails().getUsername();
        return repository.findStudentByEmailIgnoreCase(email)
                .orElseThrow(() -> new CustomException("student not found", HttpStatus.NOT_FOUND));
    }
}
