package com.example.springdataexercise;

import com.example.springdataexercise.services.AuthorService;
import com.example.springdataexercise.services.BookService;
import com.example.springdataexercise.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class ConsoleRunner implements CommandLineRunner {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public ConsoleRunner(BookService bookService, AuthorService authorService, CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) {
        seedInfo();

        System.out.println("first exercise:");
        bookService.printBooksAfter2000year();
        System.out.println("second exercise:");
        bookService.printAuthorsWithBooksBefore(1990);
        System.out.println("third exercise:");
        authorService.printAuthorsOrderedByBooksCount();
    }


    private void seedInfo() {
        try {
            categoryService.seedCategory();
            authorService.seedAuthors();
            bookService.seedBooks();
        } catch (Exception e) {
            System.out.println("Error in seeding info.");
        }
    }

}
