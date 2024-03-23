//package com.example.learning.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Table(
//        name = "tbl_student",
//        uniqueConstraints = @UniqueConstraint(
//                name="emailid_unique",
//                columnNames = {"email_address"}
//        )
//)
//public class Student {
//
//    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "student_sequence"
//    )
//    @SequenceGenerator(
//            name = "student_sequence",
//            allocationSize = 1
//    )
//    private Long studentId;
//
//    private String firstName;
//
//    private String lastName;
//
//    @Column(name = "email_address", nullable = false)
//    private String emailId;
//
//    private String guardianName;
//
//    private String guardingMobile;
//
//    private String guardianEmailId;
//
//}
