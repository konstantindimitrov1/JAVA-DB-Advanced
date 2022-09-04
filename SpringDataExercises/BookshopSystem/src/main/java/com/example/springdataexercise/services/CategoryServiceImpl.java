package com.example.springdataexercise.services;

import com.example.springdataexercise.entities.Category;
import com.example.springdataexercise.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void seedCategory() throws IOException {
        if (categoryRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of("src/main/resources/files/categories.txt"))
                .stream().filter(row -> !row.isBlank())
                .forEach(c -> {
                    Category category = new Category(c);
                    categoryRepository.save(category);
                });
    }

    @Override
    public Set<Category> getRandomCategories() {
        Random random = new Random();

        Set<Category> categories = new HashSet<>();

        for (int i = 0; i < 5; i++) {
            long randomCategoryId = random.nextLong(1 , categoryRepository.count());

            Category referenceById = categoryRepository.findById(randomCategoryId).orElse(null);

            categories.add(referenceById);
        }

        return categories;
    }
}
