package com.example.bookportal.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    @NotBlank(message = "book name cannot be blank")
    private String name;

//    private List<Long> authorIds;

    private List<AuthorRequest> authors;

//    private Long categoryId;

    private String categoryName;
}
