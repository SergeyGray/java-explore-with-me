package ru.practicum.hit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HitRepository extends JpaRepository <Hit, Integer> {
}
