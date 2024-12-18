package com.rcc.dev.backend.service.slider.impl;

import com.rcc.dev.backend.constant.ResponseCode;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.dto.slider.SliderRequest;
import com.rcc.dev.backend.model.Slider;
import com.rcc.dev.backend.repository.SliderRepository;
import com.rcc.dev.backend.service.slider.iservice.SliderService;
import com.rcc.dev.backend.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SliderServiceImpl implements SliderService {

    private final SliderRepository sliderRepository;

    @Override
    public RCCResponse<Object> saveSlider(SliderRequest sliderRequest) {
        try {
            Slider slider = new Slider();
            slider.setId(sliderRequest.getId());
            slider.setTitle(sliderRequest.getTitle());
            slider.setDescription(sliderRequest.getDescription());
            slider.setGalleryId(slider.getGalleryId());
            var saved = sliderRepository.save(slider);

            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_SAVE_DATA,
                    ResponseCode.CommonEng.SUCCESS_SAVE_DATA,
                    saved
            );
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }

    @Override
    public RCCResponse<Object> getAllSlider() {
        try{
            var sliders = sliderRepository.findAllSlider();
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_ALL_DATA,
                    ResponseCode.CommonEng.SUCCESS_GET_ALL_DATA,
                    sliders
            );
        }catch (Exception e){
            log.info("list : ", e.getMessage());
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }

    @Override
    public RCCResponse<Object> getDetailSlider(Long id) {
        return null;
    }

    @Override
    public RCCResponse<Object> deleteSlider(Long id) {
        return null;
    }
}
