package com.chinwendu.studentservice.dtos.response;

import com.chinwendu.studentservice.model.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentResponse {
    private String arabicName;
    private String englishName;
    private String email;
    private String telephoneNumber;
    private String address;

    public StudentResponse(Student student) {
        this.arabicName = student.getArabicName();
        this.englishName = student.getEnglishName();
        this.email = student.getEmail();
        this.telephoneNumber = student.getTelephoneNumber();
        this.address = student.getAddress();
    }
}
