package com.rcc.dev.backend.controller;

import com.rcc.dev.backend.dto.announcement.AnnouncementRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.service.announcement.iservice.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/announcement")
@RequiredArgsConstructor
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @PostMapping("/save")
    public RCCResponse<Object> update(@RequestBody AnnouncementRequest announcementRequest){
        return announcementService.update(announcementRequest);
    }

    @GetMapping("/list")
    public RCCResponse<Object> list() {
        return announcementService.list();
    }
}
