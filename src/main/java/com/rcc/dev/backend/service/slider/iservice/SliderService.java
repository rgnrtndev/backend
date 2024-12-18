package com.rcc.dev.backend.service.slider.iservice;

import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.dto.slider.SliderRequest;
import com.rcc.dev.backend.model.Slider;
import com.rcc.dev.backend.repository.SliderRepository;

import java.util.List;

public interface SliderService {
    RCCResponse<Object> saveSlider(SliderRequest sliderRequest);
    RCCResponse<Object> getAllSlider();
    RCCResponse<Object> getDetailSlider(Long id);
    RCCResponse<Object> deleteSlider(Long id);
}
