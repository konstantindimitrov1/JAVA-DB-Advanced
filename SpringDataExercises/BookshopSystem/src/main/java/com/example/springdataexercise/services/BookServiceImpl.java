package com.example.springdataexercise.services;

import com.example.springdataexercise.entities.Author;
import com.example.springdataexercise.entities.Book;
import com.example.springdataexercise.entities.Category;
import com.example.springdataexercise.enums.AgeRestriction;
import com.example.springdataexercise.enums.BookEdition;
import com.example.springdataexercise.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void printBooksAfter2000year() {
        LocalDate localDate = LocalDate.of(2000, 1, 1);
        bookRepository.getBooksByReleaseDateAfter(localDate).forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void printAuthorsWithBooksBefore(int year) {
        bookRepository.getBooksByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(Book::getAuthor)
                .distinct()
                .forEach(a -> System.out.printf("%s %s\n", a.getFirstName(), a.getLastName()));
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of("src/main/resources/files/books.txt"))
                .stream()
                .filter(row -> !row.isBlank())
                .forEach(b -> {
                    String[] tokens = b.split("\\s+");

                    BookEdition bookType = BookEdition.values()[Integer.parseInt(tokens[0])];

                    int year = Integer.parseInt(tokens[1].split("/")[2]);
                    int month = Integer.parseInt(tokens[1].split("/")[1]);
                    int day = Integer.parseInt(tokens[1].split("/")[0]);

                    LocalDate releaseDate = LocalDate.of(year, month, day);

                    int copies = Integer.parseInt(tokens[2]);

                    BigDecimal price = new BigDecimal(tokens[3]);

                    AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(tokens[4])];

                    StringBuilder title = new StringBuilder();

                    for (int i = 5; i < tokens.length; i++) {
                        title.append(tokens[i]).append(" ");
                    }

                    String bookTitle = title.toString().trim();

                    Book book = new Book(bookTitle, bookType, price, copies, ageRestriction);

                    book.setReleaseDate(releaseDate);

                    Author author = authorService.getRandomAuthor();

                    book.setAuthor(author);

                    Set<Category> categorySet = categoryService.getRandomCategories();

                    book.setCategories(categorySet);

                    bookRepository.save(book);
                });
    }
}
