package com.rcc.dev.backend.dto.gallery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GalleryRequest {
    private Long id;
    private String title;
    private String imageBase64;
    private String description;
    private Long categoryId;
}
