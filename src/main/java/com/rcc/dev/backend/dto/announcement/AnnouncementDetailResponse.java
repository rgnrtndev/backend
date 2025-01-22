package com.rcc.dev.backend.dto.announcement;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class AnnouncementDetailResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -3846742617936700386L;
    private Long id;
    private String title;
    private String description;
    private Long galleryId;
    private String base64;
}
