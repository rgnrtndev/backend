package com.rcc.dev.backend.util;

import com.rcc.dev.backend.dto.response.RCCResponse;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtil {

    public static <T>RCCResponse <T> response(String code, String messageIdn, String messageEng, T result){
        RCCResponse<T> response = new RCCResponse<>();
        response.setCode(code);
        response.setMessageIdn(messageIdn);
        response.setMessageEng(messageEng);
        response.setResult(result);
        return response;
    }

    public static <T>RCCResponse <T> response(String code, String messageIdn, String messageEng){
        RCCResponse<T> response = new RCCResponse<>();
        response.setCode(code);
        response.setMessageIdn(messageIdn);
        response.setMessageEng(messageEng);
        return response;
    }
}
