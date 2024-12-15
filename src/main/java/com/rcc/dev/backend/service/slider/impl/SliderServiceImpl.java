package com.rcc.dev.backend.service.slider.impl;

import com.rcc.dev.backend.dto.slider.SliderRequest;
import com.rcc.dev.backend.model.Slider;
import com.rcc.dev.backend.repository.SliderRepository;
import com.rcc.dev.backend.service.slider.iservice.SliderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SliderServiceImpl implements SliderService {

    private final SliderRepository sliderRepository;

    @Override
    public Slider saveSlider(SliderRequest sliderRequest) {
        Slider slider = new Slider();
        slider.setId(sliderRequest.getId());
        slider.setTitle(sliderRequest.getTitle());
        slider.setDescription(sliderRequest.getDescription());
        slider.setImgBase64(sliderRequest.getImgBase64());
        return slider;
    }

    @Override
    public List<Slider> getAllSlider() {
        return List.of();
    }

    @Override
    public Slider getDetailSlider(Long id) {
        return null;
    }
}
