package com.rcc.dev.backend.service.announcement.iservice;

import com.rcc.dev.backend.dto.announcement.AnnouncementRequest;
import com.rcc.dev.backend.model.Announcement;

import java.util.List;

public interface AnnouncementService {
    List<Announcement> list();
    Announcement detail(Long id);
    Announcement update(AnnouncementRequest announcementRequest);
    void delete(Long id);
}
