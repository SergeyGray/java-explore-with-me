package ru.practicum.main_server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.main_server.model.Compilation;

public interface CompilationRepository extends JpaRepository<Compilation, Integer> {
    @Query(value = "select  c from Compilation  as c  where c.pinned = ?1")
    Page<Compilation> getCompilations(Boolean pinned, Pageable pageable);

    @Query(value = "select  c from Compilation  as c")
    Page<Compilation> getCompilationNotParam(Pageable pageable);
}
