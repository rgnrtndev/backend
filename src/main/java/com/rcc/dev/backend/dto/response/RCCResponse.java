package com.rcc.dev.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rcc.dev.backend.constant.StatusResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RCCResponse <T> extends StatusResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 5926727275902140116L;

    @JsonProperty(index = 4)
    private T result;
}