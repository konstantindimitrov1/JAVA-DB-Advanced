package com.example.springintro.repository;

import com.example.springintro.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a ORDER BY size(a.books) DESC")
    List<Author> findAllByBooksSizeDESC();

    List<Author> findAllByFirstNameEndingWith(String firstName);

    @Query("select sum(b.copies) from Book b where concat(b.author.firstName, ' ', b.author.lastName) like :authorName")
    int getTotalCopies(String authorName);
}
