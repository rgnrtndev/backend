package com.rcc.dev.backend.service.gallery.impl;

import com.rcc.dev.backend.constant.ResponseCode;
import com.rcc.dev.backend.constant.StatusResponse;
import com.rcc.dev.backend.dto.gallery.GalleryRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.Gallery;
import com.rcc.dev.backend.repository.GalleryRepository;
import com.rcc.dev.backend.service.gallery.iservice.GalleryService;
import com.rcc.dev.backend.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryRepository;

    @Override
    public RCCResponse<Object> findAll() {
        try {
            var galleries = galleryRepository.findAll();
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_ALL_DATA,
                    ResponseCode.CommonEng.SUCCESS_GET_ALL_DATA,
                    galleries
            );
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }

    @Override
    public RCCResponse<Object> save(GalleryRequest galleryRequest) {
        try{
            Gallery mapGallery = Gallery.builder()
                    .id(galleryRequest.getId())
                    .title(galleryRequest.getTitle())
                    .description(galleryRequest.getDescription())
                    .imageBase64(galleryRequest.getImageBase64())
                    .categoryId(galleryRequest.getCategoryId())
                    .build();
            Gallery savedGallery = galleryRepository.save(mapGallery);
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_SAVE_DATA,
                    ResponseCode.CommonEng.SUCCESS_SAVE_DATA,
                    savedGallery
            );
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }

    @Override
    public RCCResponse<Object> detail(Long id) {
        var galleryDetail = galleryRepository.findById(id);
        if(galleryDetail.isPresent()){
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_DATA_DETAIL,
                    ResponseCode.CommonEng.SUCCESS_GET_DATA_DETAIL,
                    galleryDetail
            );
        }
        return ResponseUtil.response(
                ResponseCode.SUCCESS_RESPONSE_CODE,
                ResponseCode.CommonIdn.DATA_NOT_FOUND,
                ResponseCode.CommonEng.DATA_NOT_FOUND,
                galleryDetail
        );
    }
}
