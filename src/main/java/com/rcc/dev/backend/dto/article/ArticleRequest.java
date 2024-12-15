package com.rcc.dev.backend.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequest {
    private Long id;
    private String description;
    private Long galleryId;
}