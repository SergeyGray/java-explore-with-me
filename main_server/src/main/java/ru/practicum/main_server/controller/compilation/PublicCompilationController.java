package ru.practicum.main_server.controller.compilation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main_server.dto.CompilationDto;
import ru.practicum.main_server.mapper.CompilationMapper;
import ru.practicum.main_server.model.Compilation;
import ru.practicum.main_server.service.compilation_service.CompilationService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/compilations")
public class PublicCompilationController {
    private final CompilationService compilationService;

    @GetMapping
    public List<CompilationDto> getCompilations(@RequestParam(required = false) Boolean pinned,
                                                @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                                @RequestParam(defaultValue = "10") @Positive int size) {
        List<Compilation> compilations = compilationService.getCompilations(pinned, from, size);
        return CompilationMapper.toCompilationDtoList(compilations);
    }

    @GetMapping("{compId}")
    public CompilationDto getCompilationId(@PathVariable int compId) {
        Compilation compilation = compilationService.getCompilationId(compId);
        return CompilationMapper.toCompilationDto(compilation);
    }
}
