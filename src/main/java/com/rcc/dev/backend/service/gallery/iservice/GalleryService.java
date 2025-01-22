package com.rcc.dev.backend.service.gallery.iservice;

import com.rcc.dev.backend.dto.gallery.GalleryRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.Gallery;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GalleryService {
    ResponseEntity<RCCResponse<Object>> findAll();
    ResponseEntity<RCCResponse<Object>> save(GalleryRequest galleryRequest);
    ResponseEntity<RCCResponse<Object>> detail(Long id);
}
