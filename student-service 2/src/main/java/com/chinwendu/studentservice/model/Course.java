package com.chinwendu.studentservice.model;

import com.chinwendu.studentservice.audit.Audit;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "course-code")
    private String courseCode;
    private String courseTitle;
    private String courseDescription;
}
