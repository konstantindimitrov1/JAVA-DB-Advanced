package com.example.springdataexercise.services;

import java.io.IOException;

public interface BookService {
    void seedBooks() throws IOException;

    void printBooksAfter2000year();

    void printAuthorsWithBooksBefore(int year);
}
