package ru.practicum.main_server.controller.compilation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main_server.dto.CompilationDto;
import ru.practicum.main_server.dto.NewCompilationDto;
import ru.practicum.main_server.mapper.CompilationMapper;
import ru.practicum.main_server.marker.Marker;
import ru.practicum.main_server.model.Compilation;
import ru.practicum.main_server.service.compilation_service.CompilationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/compilations")
public class AdminCompilationController {
    private final CompilationService compilationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto create(@RequestBody @Validated({Marker.Create.class}) NewCompilationDto newCompilationDto) {
        Compilation compilation = compilationService.create(newCompilationDto);
        return CompilationMapper.toCompilationDto(compilation);
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteId(@PathVariable int compId) {
        compilationService.deleteId(compId);
    }

    @PatchMapping("/{compId}")
    public CompilationDto update(@RequestBody @Validated(Marker.Update.class) NewCompilationDto newCompilationDto,
                                 @PathVariable int compId) {
        Compilation compilation = compilationService.update(newCompilationDto, compId);
        return CompilationMapper.toCompilationDto(compilation);
    }
}
