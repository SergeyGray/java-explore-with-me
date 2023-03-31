package ru.practicum.main_server.mapper;

import ru.practicum.main_server.dto.CategoryCreateDto;
import ru.practicum.main_server.dto.CategoryDto;
import ru.practicum.main_server.model.Category;

public class CategoryMapper {

    public static Category categoryCreateDtoToCategory(CategoryCreateDto categoryCreateDto) {
        return new Category(categoryCreateDto.getName());
    }

    public static Category categoryCreateDtoToCategory(CategoryCreateDto categoryCreateDto, int id) {
        return new Category(id, categoryCreateDto.getName());
    }

    public static CategoryDto categoryToDto(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }

    public static Category dtoToCategory(CategoryDto categoryDto) {
        return new Category(categoryDto.getId(), categoryDto.getName());
    }
}
