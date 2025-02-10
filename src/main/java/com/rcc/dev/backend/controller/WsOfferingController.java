package com.rcc.dev.backend.controller;

import com.rcc.dev.backend.service.offering.iservice.OfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class WsOfferingController {
    private final OfferingService offeringService;

    @MessageMapping("/offerings")
    @SendTo("/topic/offering")
    public Map<String, Object> getOfferingChart() {
        return offeringService.getOfferingChart();  // Fetch offering data
    }
}
