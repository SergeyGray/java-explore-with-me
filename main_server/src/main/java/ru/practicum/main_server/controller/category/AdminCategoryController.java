package ru.practicum.main_server.controller.category;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main_server.dto.CategoryCreateDto;
import ru.practicum.main_server.dto.CategoryDto;
import ru.practicum.main_server.mapper.CategoryMapper;
import ru.practicum.main_server.service.category_service.CategoryService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    private final CategoryService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto create(@RequestBody @Valid CategoryCreateDto categoryCreateDto) {
        log.info("Service started");
        return service.create(CategoryMapper.categoryCreateDtoToCategory(categoryCreateDto));
    }

    @PatchMapping("/{catId}")
    public CategoryDto update(@RequestBody @Valid CategoryCreateDto categoryCreateDto, @PathVariable int catId) {
        return service.update(CategoryMapper.categoryCreateDtoToCategory(categoryCreateDto, catId));
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteId(@PathVariable int catId) {
        service.deleteById(catId);
    }
}
