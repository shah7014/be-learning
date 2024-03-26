package com.example.bookportal.service;

import com.example.bookportal.dto.request.AuthorRequest;
import com.example.bookportal.dto.response.AuthorResponse;
import com.example.bookportal.entity.Author;
import com.example.bookportal.entity.Zipcode;
import com.example.bookportal.exception.NotFoundException;
import com.example.bookportal.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final ZipcodeService zipcodeService;


    public AuthorResponse addAuthor(AuthorRequest authorRequest) throws NotFoundException {
        Zipcode zipcode = zipcodeService.getZipCode(authorRequest.getZipcodeId());
        Author author = Author.builder()
                .name(authorRequest.getName())
                .zipcode(zipcode)
                .build();
        return AuthorResponse.getAsAuthorResponse(authorRepository.save(author));
    }

    public AuthorResponse getAuthorById(Long authorId) throws NotFoundException {
        return AuthorResponse.getAsAuthorResponse(getAuthor(authorId));
    }

    private Author getAuthor(Long authorId) throws NotFoundException {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Author with id: " + authorId + " not found"));
    }


    public List<AuthorResponse> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(author -> AuthorResponse.getAsAuthorResponse(author))
                .toList();
    }

    public AuthorResponse deleteAuthor(Long authorId) throws NotFoundException {
        Author author = getAuthor(authorId);
        authorRepository.delete(author);
        return AuthorResponse.getAsAuthorResponse(author);
    }
}
