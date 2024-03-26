package com.example.bookportal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Author {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

//    REM: cascading not needed as if we delete author we don't want to delete the zip code
    @ManyToOne
    @JoinColumn(name = "zipcode_id")
    private Zipcode zipcode;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();
}
