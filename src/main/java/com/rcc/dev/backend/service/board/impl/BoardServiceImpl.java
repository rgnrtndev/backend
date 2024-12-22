package com.rcc.dev.backend.service.board.impl;

import com.rcc.dev.backend.constant.ResponseCode;
import com.rcc.dev.backend.dto.board.BoardRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.Board;
import com.rcc.dev.backend.repository.BoardRepository;
import com.rcc.dev.backend.service.board.iservice.BoardService;
import com.rcc.dev.backend.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public RCCResponse<Object> list() {
        try {
            var boards = boardRepository.findAll();
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_ALL_DATA,
                    ResponseCode.CommonEng.SUCCESS_GET_ALL_DATA,
                    boards
            );
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }

    @Transactional
    @Override
    public RCCResponse<Object> saved(BoardRequest boardRequest) {
        try {
            Board mapBoard = Board.builder()
                    .id(boardRequest.getId())
                    .name(boardRequest.getName())
                    .roleId(boardRequest.getRoleId())
                    .galleryId(boardRequest.getGalleryId())
                    .phoneNumber(boardRequest.getPhoneNumber())
                    .startPeriod(boardRequest.getStartPeriod())
                    .endPeriod(boardRequest.getEndPeriod())
                    .build();

            var savedBoard = boardRepository.save(mapBoard);
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_SAVE_DATA,
                    ResponseCode.CommonEng.SUCCESS_SAVE_DATA,
                    savedBoard
            );
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }

    @Override
    public RCCResponse<Object> detail(Long id) {
        try {
            var board = boardRepository.findById(id);
            return board.<RCCResponse<Object>>map(value -> ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_DATA_DETAIL,
                    ResponseCode.CommonEng.SUCCESS_GET_DATA_DETAIL,
                    value
            )).orElseGet(() -> ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.DATA_NOT_FOUND,
                    ResponseCode.CommonEng.DATA_NOT_FOUND,
                    board
            ));
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }

    @Transactional
    @Override
    public RCCResponse<Object> delete(Long id) {
        try {
            Board board = boardRepository.findById(id).get();
            boardRepository.delete(board);
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_DELETED_DATA,
                    ResponseCode.CommonEng.SUCCESS_DELETED_DATA
            );
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }
}
