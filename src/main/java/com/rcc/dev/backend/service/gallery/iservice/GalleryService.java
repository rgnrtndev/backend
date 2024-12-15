package com.rcc.dev.backend.service.gallery.iservice;

import com.rcc.dev.backend.dto.gallery.GalleryRequest;
import com.rcc.dev.backend.model.Gallery;

import java.util.List;

public interface GalleryService {
    List<Gallery> findAll();
    Gallery save(GalleryRequest galleryRequest);
    Gallery detail(Long id);
}
