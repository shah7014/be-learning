package com.example.learning.repository;

import com.example.learning.entity.Author;
import org.springframework.data.jpa.repository.
        JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
