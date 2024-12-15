package com.rcc.dev.backend.service.slider.iservice;

import com.rcc.dev.backend.dto.slider.SliderRequest;
import com.rcc.dev.backend.model.Slider;
import com.rcc.dev.backend.repository.SliderRepository;

import java.util.List;

public interface SliderService {
    Slider saveSlider(SliderRequest sliderRequest);
    List<Slider> getAllSlider();
    Slider getDetailSlider(Long id);
}
