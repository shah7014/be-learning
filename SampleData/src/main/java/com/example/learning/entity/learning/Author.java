package com.example.learning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Author {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 35)
    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private int age;

//    course is the owner of many-to-many relationship. so this is now owner side
//    to maintain bidirectional relationship we need to use mappedBy
    @ManyToMany(mappedBy = "authors")
    private List<Course> courses;

//    @Column(insertable = true, updatable = false, nullable = false)
//    private LocalDateTime createdAt;
//
//    @Column(insertable = false, updatable = true)
//    private LocalDateTime updatesAt;
}
