package com.rcc.dev.backend.service.announcement.iservice;

import com.rcc.dev.backend.dto.announcement.AnnouncementRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface AnnouncementService {
    ResponseEntity<RCCResponse<Object>> list(HttpServletRequest httpServletRequest);
    ResponseEntity<RCCResponse<Object>> detail(HttpServletRequest httpServletRequest, Long id);
    ResponseEntity<RCCResponse<Object>> update(HttpServletRequest httpServletRequest, AnnouncementRequest announcementRequest);
    ResponseEntity<RCCResponse<Object>> delete(HttpServletRequest httpServletRequest, Long id);
}
