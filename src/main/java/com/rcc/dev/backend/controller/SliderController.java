package com.rcc.dev.backend.controller;

import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.dto.slider.SliderRequest;
import com.rcc.dev.backend.model.Slider;
import com.rcc.dev.backend.service.slider.iservice.SliderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/slider")
public class SliderController {

    @Autowired
    private SliderService sliderService;

    @PostMapping("/save")
    public RCCResponse<Object> saveSlider(@RequestBody SliderRequest sliderRequest){
        return sliderService.saveSlider(sliderRequest);
    }

    @GetMapping("/list")
    public RCCResponse<Object> listSlider(){
        return sliderService.getAllSlider();
    }
}
