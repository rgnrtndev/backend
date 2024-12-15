package com.rcc.dev.backend.controller;

import com.rcc.dev.backend.dto.announcement.AnnouncementRequest;
import com.rcc.dev.backend.model.Announcement;
import com.rcc.dev.backend.service.announcement.iservice.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/announcement")
@RequiredArgsConstructor
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @PostMapping("/save")
    public Announcement update(@RequestBody AnnouncementRequest announcementRequest){
        return announcementService.update(announcementRequest);
    }
}
