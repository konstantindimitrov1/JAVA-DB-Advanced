package com.example.springintro.service;

import com.example.springintro.model.entity.Book;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    void printBookTitlesWithGivenAgeRestriction(String ageRestriction);

    void printGoldenEditionBooksWithLessThanCopies(int copies);

    void printAllBooksByPriceLessThanOrPriceGreaterThan(BigDecimal lowerBound, BigDecimal higherBound);

    void printAllBooksNotInGivenYear(int year);

    void printBooksByReleaseDateIsLessThan(LocalDate releaseDate);

    void printBooksByTitleContainingIgnoreCase(String str);

    int getBooksCountWithTitleLongerThanGivenNumber(int number);
}
