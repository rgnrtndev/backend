package com.rcc.dev.backend.service.announcement.iservice;

import com.rcc.dev.backend.dto.announcement.AnnouncementRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AnnouncementService {
    RCCResponse<Object> list(HttpServletRequest httpServletRequest);
    RCCResponse<Object> detail(HttpServletRequest httpServletRequest, Long id);
    RCCResponse<Object> update(HttpServletRequest httpServletRequest, AnnouncementRequest announcementRequest);
    RCCResponse<Object> delete(HttpServletRequest httpServletRequest, Long id);
}
