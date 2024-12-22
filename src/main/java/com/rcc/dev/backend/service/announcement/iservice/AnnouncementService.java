package com.rcc.dev.backend.service.announcement.iservice;

import com.rcc.dev.backend.dto.announcement.AnnouncementRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;

public interface AnnouncementService {
    RCCResponse<Object> list();
    RCCResponse<Object> detail(Long id);
    RCCResponse<Object> update(AnnouncementRequest announcementRequest);
    RCCResponse<Object> delete(Long id);
}
