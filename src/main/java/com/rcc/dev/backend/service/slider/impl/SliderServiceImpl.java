package com.rcc.dev.backend.service.slider.impl;

import com.rcc.dev.backend.constant.ResponseCode;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.dto.slider.SliderRequest;
import com.rcc.dev.backend.model.Slider;
import com.rcc.dev.backend.repository.SliderRepository;
import com.rcc.dev.backend.service.slider.iservice.SliderService;
import com.rcc.dev.backend.util.CacheUtil;
import com.rcc.dev.backend.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class SliderServiceImpl implements SliderService {

    private final CacheUtil cacheUtil;
    private final SliderRepository sliderRepository;

    @Transactional
    @Override
    public ResponseEntity<RCCResponse<Object>> saveSlider(SliderRequest sliderRequest) {
        try {
            Slider slider;
            if(Objects.isNull(sliderRequest.getId()) || sliderRequest.getId().equals(0L)){
                slider = new Slider();
            }else{
                var slide = sliderRepository.findById(sliderRequest.getId());
                slider = slide.orElse(null);
            }
            assert slider != null;
            slider.setId(sliderRequest.getId());
            slider.setTitle(sliderRequest.getTitle());
            slider.setDescription(sliderRequest.getDescription());
            slider.setGalleryId(sliderRequest.getGalleryId());
            var saved = sliderRepository.save(slider);

            var sliders = sliderRepository.findAll();
//            cacheUtil.putCache("slider", sliders);

            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_SAVE_DATA,
                    ResponseCode.CommonEng.SUCCESS_SAVE_DATA,
                    saved
            ));
        }catch (Exception e){
            log.info("error ", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            ));
        }
    }

    @Override
    public ResponseEntity<RCCResponse<Object>> getAllSlider() {
        try{
//            var sliders = cacheUtil.getCache("slider");
//            if(Objects.isNull(sliders)) {
//            }
            var sliders = sliderRepository.findAllSlider();
//            cacheUtil.putCache("slider", sliders);
            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_ALL_DATA,
                    ResponseCode.CommonEng.SUCCESS_GET_ALL_DATA,
                    sliders
            ));
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtil.response(
                        ResponseCode.ERROR_RESPONSE_CODE,
                        ResponseCode.CommonIdn.ERROR,
                        ResponseCode.CommonEng.ERROR
            ));
        }
    }

    @Override
    public ResponseEntity<RCCResponse<Object>> getDetailSlider(Long id) {
        return null;
    }

    @Transactional
    @Override
    public ResponseEntity<RCCResponse<Object>> deleteSlider(Long id) {
        return null;
    }
}
