package com.rcc.dev.backend.service.announcement.impl;

import com.rcc.dev.backend.constant.ResponseCode;
import com.rcc.dev.backend.dto.announcement.AnnouncementDetailResponse;
import com.rcc.dev.backend.dto.announcement.AnnouncementRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.exception.UnauthorizedException;
import com.rcc.dev.backend.model.Announcement;
import com.rcc.dev.backend.repository.AnnouncementRepository;
import com.rcc.dev.backend.repository.GalleryRepository;
import com.rcc.dev.backend.service.announcement.iservice.AnnouncementService;
import com.rcc.dev.backend.util.AuthenticationUtils;
import com.rcc.dev.backend.util.CacheUtil;
import com.rcc.dev.backend.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final CacheUtil cacheUtil;
    private final AnnouncementRepository announcementRepository;
    private final GalleryRepository galleryRepository;
    private final AuthenticationUtils authenticationUtils;

    @Override
    public ResponseEntity<RCCResponse<Object>> list(HttpServletRequest httpServletRequest) {
        try {
            var announcements = announcementRepository.findAllSlider();
            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_ALL_DATA,
                    ResponseCode.CommonEng.SUCCESS_GET_ALL_DATA,
                    announcements
            ));
        }catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    ResponseUtil.response(
                            ResponseCode.ERROR_RESPONSE_CODE,
                            e.getMessage(),
                            e.getMessage()
                    )
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            ));
        }
    }

    @Override
    public ResponseEntity<RCCResponse<Object>> detail(HttpServletRequest httpServletRequest, Long id) {
        try {
            AnnouncementDetailResponse detailResponse = new AnnouncementDetailResponse();
            var announcement = announcementRepository.findById(id);
            if(announcement.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ResponseUtil.response(
                                ResponseCode.SUCCESS_RESPONSE_CODE,
                                ResponseCode.CommonIdn.DATA_NOT_FOUND,
                                ResponseCode.CommonEng.DATA_NOT_FOUND
                ));
            }
            var image = galleryRepository.findById(announcement.get().getGalleryId()).get();
            detailResponse.setId(announcement.get().getId());
            detailResponse.setTitle(announcement.get().getTitle());
            detailResponse.setDescription(announcement.get().getDescription());
            detailResponse.setGalleryId(announcement.get().getGalleryId());
            detailResponse.setBase64(image.getImageBase64());
            return ResponseEntity.ok(
                    ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_DATA_DETAIL,
                    ResponseCode.CommonEng.SUCCESS_GET_DATA_DETAIL,
                    detailResponse
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            ));
        }
    }

    @Transactional
    @Override
    public ResponseEntity<RCCResponse<Object>> update(HttpServletRequest httpServletRequest, AnnouncementRequest announcementRequest) {
        var userData = authenticationUtils.validateAuthentication(httpServletRequest);
        try {
            Announcement mapAnnouncement = Announcement.builder()
                    .id(announcementRequest.getId())
                    .title(announcementRequest.getTitle())
                    .galleryId(announcementRequest.getGalleryId())
                    .description(announcementRequest.getDescription())
                    .createdBy(userData.getId())
                    .build();
            var savedAnnouncement = announcementRepository.save(mapAnnouncement);
            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_SAVE_DATA,
                    ResponseCode.CommonEng.SUCCESS_SAVE_DATA,
                    savedAnnouncement
            ));
        }catch (UnauthorizedException e) {
            // Handle token expired or unauthorized access
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    ResponseUtil.response(
                            ResponseCode.ERROR_RESPONSE_CODE,
                            e.getMessage(),
                            e.getMessage()
                    )
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            ));
        }
    }

    @Transactional
    @Override
    public ResponseEntity<RCCResponse<Object>> delete(HttpServletRequest httpServletRequest, Long id) {
        var userData = authenticationUtils.validateAuthentication(httpServletRequest);
        try {
            var announcement = announcementRepository.findById(id);
            if(announcement.isEmpty()){
                return ResponseEntity.status(201).body(ResponseUtil.response(
                        ResponseCode.SUCCESS_RESPONSE_CODE,
                        ResponseCode.CommonIdn.DATA_NOT_FOUND,
                        ResponseCode.CommonEng.DATA_NOT_FOUND,
                        announcement
                ));
            }
            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_DELETED_DATA,
                    ResponseCode.CommonEng.SUCCESS_DELETED_DATA
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            ));
        }
    }
}
