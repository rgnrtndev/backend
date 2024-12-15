package com.rcc.dev.backend.service.gallery.impl;

import com.rcc.dev.backend.dto.gallery.GalleryRequest;
import com.rcc.dev.backend.model.Gallery;
import com.rcc.dev.backend.repository.GalleryRepository;
import com.rcc.dev.backend.service.gallery.iservice.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryRepository;

    @Override
    public List<Gallery> findAll() {
        return galleryRepository.findAll();
    }

    @Override
    public Gallery save(GalleryRequest galleryRequest) {
        Gallery gallery = Gallery.builder()
                .id(galleryRequest.getId())
                .title(galleryRequest.getTitle())
                .description(galleryRequest.getDescription())
                .imageBase64(galleryRequest.getImageBase64())
                .categoryId(galleryRequest.getCategoryId())
                .createdBy(1L)
                .createdDate(new Date())
                .updatedBy(1L)
                .updatedDate(new Date())
                .build();
        return galleryRepository.save(gallery);
    }

    @Override
    public Gallery detail(Long id) {
        return galleryRepository.findById(id).get();
    }
}
