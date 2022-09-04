package com.example.springdataexercise.services;

import com.example.springdataexercise.entities.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategory() throws IOException;

    Set<Category> getRandomCategories();
}
