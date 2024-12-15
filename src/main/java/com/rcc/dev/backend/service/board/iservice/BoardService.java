package com.rcc.dev.backend.service.board.iservice;

import com.rcc.dev.backend.dto.board.BoardRequest;
import com.rcc.dev.backend.model.Board;

import java.util.List;

public interface BoardService {
    List<Board> list();
    Board saved(BoardRequest boardRequest);
    Board detail(Long id);
    void delete(Long id);
}
