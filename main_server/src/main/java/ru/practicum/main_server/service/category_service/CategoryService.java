package ru.practicum.main_server.service.category_service;

import ru.practicum.main_server.dto.CategoryDto;
import ru.practicum.main_server.model.Category;

import java.util.List;

public interface CategoryService {

    CategoryDto create(Category category);

    CategoryDto getById(int catId);

    List<CategoryDto> getAllCategories(int from, int size);

    CategoryDto update(Category category);

    void deleteById(int catId);


}
