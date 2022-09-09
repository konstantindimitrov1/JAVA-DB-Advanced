package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByCopiesLessThanAndEditionType(Integer copies, EditionType editionType);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal low, BigDecimal high);

    @Query("select b from Book b where year(b.releaseDate) < :year or year(b.releaseDate) > :year")
    List<Book> findAllByReleaseDateIsNotInGivenYear(@Param(value = "year") int year);

    List<Book> findAllByReleaseDateIsLessThan(LocalDate releaseDate);

    List<Book> findAllByTitleContainingIgnoreCase(String str);

    @Query("select b from Book b where b.author.lastName like :str")
    List<Book> getBooksWithAuthorsLastNameStartsWithGivenString(String str);

    @Query("select count(b) from Book b where length(b.title) > :number")
    int getBooksCountWithTitleLongerThanGivenNumber(int number);
}
