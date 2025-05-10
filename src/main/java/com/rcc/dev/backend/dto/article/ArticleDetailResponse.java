package com.rcc.dev.backend.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -4270989224351968501L;

    private Long id;
    private String description;
    private String imageBase64;
}
