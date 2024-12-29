package com.rcc.dev.backend.util;

import org.json.JSONObject;

public class CommonUtils {
    public String createJsonResponseMessage(String title, String message) {
        JSONObject messageJson = new JSONObject();

        messageJson.put("title", title);
        messageJson.put("message", message);

        return messageJson.toString();
    }
}
