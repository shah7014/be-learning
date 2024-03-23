package com.example.learning;

import com.example.learning.entity.Author;
import com.example.learning.entity.Course;
import com.example.learning.repository.AuthorRepository;
import com.example.learning.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class SampleDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleDataApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AuthorRepository authorRepository, CourseRepository courseRepository) {
        return args -> {

            Author author1 = Author.builder()
                    .age(40)
                    .firstName("dcb")
                    .lastName("dcb")
                    .email("dcb@gmail.com")
                    .build();
            Author author2 = Author.builder()
                    .age(50)
                    .firstName("fcp")
                    .lastName("fcp")
                    .email("fcp@gmail.com")
                    .build();

            authorRepository.save(author1);
            authorRepository.save(author2);

            Course course = Course.builder()
                    .name("spring boot")
                    .description("learn basics of spring boot")
                    .authors(List.of(author1, author2))
                    .build();

            courseRepository.save(course);


        };
    }

}
