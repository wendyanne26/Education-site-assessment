package com.chinwendu.studentservice.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private String arabicName;
    private String englishName;
    private String email;
    private String telephoneNumber;
    private String address;
}
