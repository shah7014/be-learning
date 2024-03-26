package com.example.bookportal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Zipcode {

    @Id
    @GeneratedValue
    private Long id;

//    REM: we don't want to allow duplicate "code" so making it unique
    @Column(unique = true)
    private String code;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "city_id")
    private City city;
}
