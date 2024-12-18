package com.rcc.dev.backend.service.gallery.iservice;

import com.rcc.dev.backend.dto.gallery.GalleryRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.Gallery;

import java.util.List;

public interface GalleryService {
    RCCResponse<Object> findAll();
    RCCResponse<Object> save(GalleryRequest galleryRequest);
    RCCResponse<Object> detail(Long id);
}
