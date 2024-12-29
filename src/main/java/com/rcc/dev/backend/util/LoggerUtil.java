package com.rcc.dev.backend.util;

import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;

@Component
public class LoggerUtil {

    public String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    public String getUid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
