package com.rcc.dev.backend.dto.announcement;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementRequest {
    private Long id;
    private String title;
    private String description;
    private Long galleryId;
}