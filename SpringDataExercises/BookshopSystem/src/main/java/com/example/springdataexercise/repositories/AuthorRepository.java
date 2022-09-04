package com.example.springdataexercise.repositories;

import com.example.springdataexercise.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "select a from Author a order by a.books.size desc")
    List<Author> getAuthorsByBooksCount();
}
