package com.rcc.dev.backend.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequest {
    private Long id;
    private String name;
    private Long roleId;
    private Long galleryId;
    private String phoneNumber;
    private Date startPeriod;
    private Date endPeriod;
}
