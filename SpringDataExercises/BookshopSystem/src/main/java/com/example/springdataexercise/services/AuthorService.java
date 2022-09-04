package com.example.springdataexercise.services;

import com.example.springdataexercise.entities.Author;

import java.io.IOException;

public interface AuthorService {

    void seedAuthors () throws IOException;

    Author getRandomAuthor();

    void printAuthorsOrderedByBooksCount();
}
