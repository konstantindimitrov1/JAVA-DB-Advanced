package com.example.springintro.service.impl;

import com.example.springintro.model.entity.*;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "C:\\Users\\Konstantin\\Downloads\\AdvancedQueryingExercise\\BookShopSystem\\src\\main\\resources\\files\\books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
       return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    @Override
    public void printBookTitlesWithGivenAgeRestriction(String ageRestriction) {
        bookRepository.findAllByAgeRestriction(AgeRestriction.valueOf(ageRestriction.toUpperCase()))
                .forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void printGoldenEditionBooksWithLessThanCopies(int copies) {
        bookRepository.findAllByCopiesLessThanAndEditionType(copies, EditionType.GOLD)
                .forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void printAllBooksByPriceLessThanOrPriceGreaterThan(BigDecimal lowerBound, BigDecimal higherBound) {
        bookRepository.findAllByPriceLessThanOrPriceGreaterThan(lowerBound, higherBound)
                .forEach(b -> System.out.println(b.getTitle() + " - " + b.getPrice()));
    }

    @Override
    public void printAllBooksNotInGivenYear(int year) {
        bookRepository.findAllByReleaseDateIsNotInGivenYear(year)
                .forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void printBooksByReleaseDateIsLessThan(LocalDate releaseDate) {
        bookRepository.findAllByReleaseDateBefore(releaseDate)
                .forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void printBooksByTitleContainingIgnoreCase(String str) {
        bookRepository.findAllByTitleContainingIgnoreCase(str)
                .forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public int getBooksCountWithTitleLongerThanGivenNumber(int number) {
        return bookRepository.getBooksCountWithTitleLongerThanGivenNumber(number);
    }

    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }
}
