package com.example.bookportal.controller;

import com.example.bookportal.dto.request.AuthorRequest;
import com.example.bookportal.dto.response.AuthorResponse;
import com.example.bookportal.exception.NotFoundException;
import com.example.bookportal.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

//    REM: we are returning authorResponse here because we want list of books lazily fetched only on need
    @PostMapping
    public ResponseEntity<AuthorResponse> addAuthor(@RequestBody @Valid AuthorRequest authorRequest) throws NotFoundException {
        AuthorResponse authorResponse = authorService.addAuthor(authorRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(authorResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthor(@PathVariable(name = "id", required = true) Long authorId) throws NotFoundException {
        AuthorResponse author = authorService.getAuthorById(authorId);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        List<AuthorResponse> authors = authorService.getAllAuthors();
        return ResponseEntity.status(HttpStatus.OK)
                .body(authors);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorResponse> deleteAuthor(@PathVariable(name = "id", required = true) Long authorId) throws NotFoundException {
        AuthorResponse deletedAuthor = authorService.deleteAuthor(authorId);
        return new ResponseEntity<>(deletedAuthor, HttpStatus.OK);
    }

//    @PutMapping("/addNewBooks")
//    public ResponseEntity<AuthorResponse> createNewBooksForAuthor(@RequestBody @Valid AuthorRequest authorRequest) {}
}
