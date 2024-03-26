package com.example.bookportal.dto.response;

import com.example.bookportal.entity.Author;
import com.example.bookportal.entity.Zipcode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorResponse {
    private Long id;
    private String name;
    private Zipcode zipcode;

    public static AuthorResponse getAsAuthorResponse(Author author) {
        return AuthorResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .zipcode(author.getZipcode())
                .build();
    }
}
