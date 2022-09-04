package com.example.springdataexercise.services;

import com.example.springdataexercise.entities.Author;
import com.example.springdataexercise.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (authorRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of("src/main/resources/files/authors.txt"))
                .forEach(a -> {
                    String[] authorNames = a.split("\\s+");

                    String firstName = authorNames[0];
                    String lastName = authorNames[1];

                    Author author = new Author(lastName);
                    author.setFirstName(firstName);

                    authorRepository.save(author);
                });
    }

    @Override
    public Author getRandomAuthor() {
        Random random = new Random();

        long randomAuthorId = random.nextLong(1, authorRepository.count());

        return authorRepository.findById(randomAuthorId).orElse(null);
    }

    @Override
    public void printAuthorsOrderedByBooksCount() {
        authorRepository.getAuthorsByBooksCount()
                .forEach(a -> System.out.printf("%s %s %d\n",
                        a.getFirstName(),
                        a.getLastName(),
                        a.getBooks().size()));
    }

}
