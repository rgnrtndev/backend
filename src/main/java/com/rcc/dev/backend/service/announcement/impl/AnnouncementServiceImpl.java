package com.rcc.dev.backend.service.announcement.impl;

import com.rcc.dev.backend.constant.ResponseCode;
import com.rcc.dev.backend.dto.announcement.AnnouncementRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.Announcement;
import com.rcc.dev.backend.repository.AnnouncementRepository;
import com.rcc.dev.backend.service.announcement.iservice.AnnouncementService;
import com.rcc.dev.backend.util.AuthenticationUtils;
import com.rcc.dev.backend.util.CacheUtil;
import com.rcc.dev.backend.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final CacheUtil cacheUtil;
    private final AnnouncementRepository announcementRepository;
    private final AuthenticationUtils authenticationUtils;

    @Override
    public RCCResponse<Object> list(HttpServletRequest httpServletRequest) {
        var userData = authenticationUtils.validateAuthentication(httpServletRequest);
        try {
            if(Objects.isNull(userData)){
                System.out.println("data not found : " + userData.getId());
            }
            var announcements = announcementRepository.findAllSlider();

            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_ALL_DATA,
                    ResponseCode.CommonEng.SUCCESS_GET_ALL_DATA,
                    announcements
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
    public RCCResponse<Object> detail(HttpServletRequest httpServletRequest, Long id) {
        try {

            var announcement = announcementRepository.findById(id);
            return announcement.<RCCResponse<Object>>map(value -> ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_DATA_DETAIL,
                    ResponseCode.CommonEng.SUCCESS_GET_DATA_DETAIL,
                    value
            )).orElseGet(() -> ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.DATA_NOT_FOUND,
                    ResponseCode.CommonEng.DATA_NOT_FOUND,
                    announcement
            ));
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }

    @Transactional
    @Override
    public RCCResponse<Object> update(HttpServletRequest httpServletRequest, AnnouncementRequest announcementRequest) {
        try {
            Announcement mapAnnouncement = Announcement.builder()
                    .id(announcementRequest.getId())
                    .title(announcementRequest.getTitle())
                    .galleryId(announcementRequest.getGalleryId())
                    .description(announcementRequest.getDescription())
                    .build();
            var savedAnnouncement = announcementRepository.save(mapAnnouncement);
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_SAVE_DATA,
                    ResponseCode.CommonEng.SUCCESS_SAVE_DATA,
                    savedAnnouncement
            );
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }

    @Transactional
    @Override
    public RCCResponse<Object> delete(HttpServletRequest httpServletRequest, Long id) {
        try {
            var announcement = announcementRepository.findById(id);
            if(announcement.isEmpty()){
                return ResponseUtil.response(
                        ResponseCode.SUCCESS_RESPONSE_CODE,
                        ResponseCode.CommonIdn.DATA_NOT_FOUND,
                        ResponseCode.CommonEng.DATA_NOT_FOUND,
                        announcement
                );
            }
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_DELETED_DATA,
                    ResponseCode.CommonEng.SUCCESS_DELETED_DATA
            );
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }
}
