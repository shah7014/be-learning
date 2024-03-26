package com.example.bookportal.service;

import com.example.bookportal.dto.request.BookRequest;
import com.example.bookportal.dto.response.BookResponse;
import com.example.bookportal.entity.Author;
import com.example.bookportal.entity.Book;
import com.example.bookportal.entity.Category;
import com.example.bookportal.entity.Zipcode;
import com.example.bookportal.exception.NotFoundException;
import com.example.bookportal.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    private final ZipcodeService zipcodeService;

    public BookResponse addBookWithNewAuthorAndNewCategory(BookRequest bookRequest) throws NotFoundException {
        Zipcode zipcode = zipcodeService.getZipCode(bookRequest.getAuthors().get(0).getZipcodeId());

        List<Author> authors = bookRequest.getAuthors().stream()
                .map(authorRequest -> Author.builder()
                        .zipcode(zipcode)
                        .name(authorRequest.getName())
                        .build())
                .toList();

        Book book = Book.builder()
                .name(bookRequest.getName())
                .authors(authors)
                .build();

        authors.forEach(author -> author.setBooks(List.of(book)));

        Book bookSaved = bookRepository.save(book);

        return BookResponse.builder()
                .name(bookSaved.getName())
                .authors(bookSaved.getAuthors().stream().map(a -> a.getName()).toList())
                .build();
    }
}
