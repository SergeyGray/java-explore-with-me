package ru.practicum.main_server.service.category_service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.main_server.dto.CategoryDto;
import ru.practicum.main_server.exception.ConflictException;
import ru.practicum.main_server.exception.NotFoundException;
import ru.practicum.main_server.mapper.CategoryMapper;
import ru.practicum.main_server.model.Category;
import ru.practicum.main_server.repository.CategoryRepository;
import ru.practicum.main_server.repository.EventRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository repository;
    private final EventRepository eventRepository;

    @Override
    public CategoryDto create(Category category) {
        return CategoryMapper.categoryToDto(repository.save(category));
    }

    @Override
    public CategoryDto getById(int catId) {
        try {
            return CategoryMapper.categoryToDto(repository.findById(catId).get());
        } catch (NoSuchElementException ex) {
            throw new NotFoundException("Категория не найдена");
        }
    }

    @Override
    public List<CategoryDto> getAllCategories(int from, int size) {
        Pageable page = PageRequest.of(from, size);
        return repository.findAll(page).getContent()
                .stream().map(CategoryMapper::categoryToDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto update(Category category) {
        Category updatedCategory = CategoryMapper.dtoToCategory(getById(category.getId()));
        updatedCategory.setName(category.getName());
        repository.save(updatedCategory);
        return CategoryMapper.categoryToDto(updatedCategory);
    }

    @Override
    public void deleteById(int catId) {
        List<Integer> events = eventRepository.findAllByCategoryId(catId);
        if (events.isEmpty()) {
            repository.deleteById(catId);
        } else {
            throw new ConflictException("Нельзя удалить категорию - есть зависимые события");
        }
    }
}
