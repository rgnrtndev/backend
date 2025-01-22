package com.rcc.dev.backend.service.slider.iservice;

import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.dto.slider.SliderRequest;
import com.rcc.dev.backend.model.Slider;
import com.rcc.dev.backend.repository.SliderRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SliderService {
    ResponseEntity<RCCResponse<Object>> saveSlider(SliderRequest sliderRequest);
    ResponseEntity<RCCResponse<Object>> getAllSlider();
    ResponseEntity<RCCResponse<Object>> getDetailSlider(Long id);
    ResponseEntity<RCCResponse<Object>> deleteSlider(Long id);
}
