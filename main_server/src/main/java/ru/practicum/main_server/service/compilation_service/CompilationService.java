package ru.practicum.main_server.service.compilation_service;

import ru.practicum.main_server.dto.NewCompilationDto;
import ru.practicum.main_server.model.Compilation;

import java.util.List;

public interface CompilationService {
    List<Compilation> getCompilations(Boolean pinned, int from, int size);

    Compilation getCompilationId(int compId);

    Compilation create(NewCompilationDto newCompilationDto);

    void deleteId(int compId);

    Compilation update(NewCompilationDto newCompilationDto, int compId);
}
