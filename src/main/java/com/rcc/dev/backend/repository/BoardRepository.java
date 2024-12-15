package com.rcc.dev.backend.repository;

import com.rcc.dev.backend.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
