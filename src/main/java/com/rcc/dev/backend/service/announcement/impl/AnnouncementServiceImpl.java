package com.rcc.dev.backend.service.announcement.impl;

import com.rcc.dev.backend.dto.announcement.AnnouncementRequest;
import com.rcc.dev.backend.model.Announcement;
import com.rcc.dev.backend.repository.AnnouncementRepository;
import com.rcc.dev.backend.service.announcement.iservice.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;


    @Override
    public List<Announcement> list() {
        return List.of();
    }

    @Override
    public Announcement detail(Long id) {
        return null;
    }

    @Override
    public Announcement update(AnnouncementRequest announcementRequest) {
        Announcement announcement = Announcement.builder()
                .id(announcementRequest.getId())
                .title(announcementRequest.getTitle())
                .galleryId(announcementRequest.getGalleryId())
                .description(announcementRequest.getDescription())
                .updatedBy(1L)
                .updateDate(new Date())
                .createdBy(1L)
                .createdDate(new Date())
                .build();
        return announcementRepository.save(announcement);
    }

    @Override
    public void delete(Long id) {

    }
}
