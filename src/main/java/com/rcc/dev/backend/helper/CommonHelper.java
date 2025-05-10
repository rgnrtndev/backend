package com.rcc.dev.backend.helper;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class CommonHelper {
    public <T> T convertBackObject(Object obj, Class<T> classTarget) {
        try {
            return classTarget.cast(obj);
        } catch (Exception e) {
            return null;
        }
    }

    public String objectToString(Object obj) {
        try {
            return new JSONObject(obj).toString();
        }catch (Exception e){
            return null;
        }
    }
}
