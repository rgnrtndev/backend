package com.rcc.dev.backend.service.board.impl;

import com.rcc.dev.backend.dto.board.BoardRequest;
import com.rcc.dev.backend.model.Board;
import com.rcc.dev.backend.repository.BoardRepository;
import com.rcc.dev.backend.service.board.iservice.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public List<Board> list() {
        return boardRepository.findAll();
    }

    @Override
    public Board saved(BoardRequest boardRequest) {
        Board board = Board.builder()
                .id(boardRequest.getId())
                .name(boardRequest.getName())
                .roleId(boardRequest.getRoleId())
                .galleryId(boardRequest.getGalleryId())
                .phoneNumber(boardRequest.getPhoneNumber())
                .startPeriod(boardRequest.getStartPeriod())
                .endPeriod(boardRequest.getEndPeriod())
                .build();
        return boardRepository.save(board);
    }

    @Override
    public Board detail(Long id) {
        return boardRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        Board board = boardRepository.findById(id).get();
        boardRepository.delete(board);
    }
}
