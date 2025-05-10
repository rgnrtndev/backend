package com.rcc.dev.backend.controller;

import com.rcc.dev.backend.dto.announcement.AnnouncementRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.service.announcement.iservice.AnnouncementService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/announcement")
@RequiredArgsConstructor
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @PostMapping("/save")
    public ResponseEntity<RCCResponse<Object>> update(HttpServletRequest httpServletRequest, @RequestBody AnnouncementRequest announcementRequest){
        return announcementService.update(httpServletRequest,announcementRequest);
    }

    @GetMapping("/list")
    public ResponseEntity<RCCResponse<Object>> list(HttpServletRequest httpServletRequest) {
        return announcementService.list(httpServletRequest);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<RCCResponse<Object>> detail(HttpServletRequest httpServletRequest, @PathVariable("id") Long id){
        return announcementService.detail(httpServletRequest, id);
    }
}
