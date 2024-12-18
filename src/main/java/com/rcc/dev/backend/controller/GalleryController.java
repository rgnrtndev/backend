package com.rcc.dev.backend.controller;

import com.rcc.dev.backend.dto.gallery.GalleryRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.Gallery;
import com.rcc.dev.backend.service.gallery.iservice.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gallery")
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryService galleryService;

    @PostMapping("/save")
    public RCCResponse<Object> save(@RequestBody GalleryRequest galleryRequest){
        return galleryService.save(galleryRequest);
    }

    @GetMapping("/detail/{id}")
    public RCCResponse<Object> detail(@PathVariable("id") Long id){
        return galleryService.detail(id);
    }

    @GetMapping("/list")
    public RCCResponse<Object> findAll(){
        return galleryService.findAll();
    }
}
