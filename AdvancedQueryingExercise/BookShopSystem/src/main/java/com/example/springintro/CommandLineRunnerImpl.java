package com.example.springintro;

import com.example.springintro.model.entity.Book;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

//         System.out.println(authorService.getTotalCopies("Randy Graham"));
//         System.out.println(bookService.getBooksCountWithTitleLongerThanGivenNumber(40));
//         bookService.printBooksByTitleContainingIgnoreCase("WOR");
//         authorService.printAuthorsWithFirstNameEndingWith("dy");
//         bookService.printBooksByReleaseDateIsLessThan(LocalDate.of(1992, 4,12));
//         bookService.printAllBooksNotInGivenYear(2000);
//         bookService.printAllBooksByPriceLessThanOrPriceGreaterThan(new BigDecimal("5"), new BigDecimal("40"));
//         bookService.printGoldenEditionBooksWithLessThanCopies(5000);
//         bookService.printBookTitlesWithGivenAgeRestriction("teEN");
//         printAllBooksAfterYear(2000);
//         printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
//         printAllAuthorsAndNumberOfTheirBooks();
//         printALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
