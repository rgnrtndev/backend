package com.rcc.dev.backend.service.board.iservice;

import com.rcc.dev.backend.dto.board.BoardRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.Board;

import java.util.List;

public interface BoardService {
    RCCResponse<Object> list();
    RCCResponse<Object> saved(BoardRequest boardRequest);
    RCCResponse<Object> detail(Long id);
    RCCResponse<Object> delete(Long id);
}
