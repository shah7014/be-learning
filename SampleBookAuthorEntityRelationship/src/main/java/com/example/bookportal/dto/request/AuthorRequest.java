package com.example.bookportal.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequest {
    @NotNull(message = "Author name can't be null")
    @NotBlank(message = "Author name can't be blank")
    private String name;

    @NotNull(message = "Zipcode id cannot be null")
    private Long zipcodeId;

    private List<Long> bookIds;
}
