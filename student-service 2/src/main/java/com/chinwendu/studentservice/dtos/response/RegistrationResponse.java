package com.chinwendu.studentservice.dtos.response;

import com.chinwendu.studentservice.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationResponse {

    private String email;
    private String token;

    public RegistrationResponse(Student student) {
        this.email = student.getEmail();
    }
}
