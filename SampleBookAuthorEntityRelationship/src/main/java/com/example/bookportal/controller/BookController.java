package com.example.bookportal.controller;

import com.example.bookportal.dto.request.BookRequest;
import com.example.bookportal.dto.response.BookResponse;
import com.example.bookportal.exception.NotFoundException;
import com.example.bookportal.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<BookResponse> addBookWIthNewAUthorAndNewCategory(@RequestBody @Valid BookRequest bookRequest) throws NotFoundException {
        BookResponse bookResponse = bookService.addBookWithNewAuthorAndNewCategory(bookRequest);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }
}
