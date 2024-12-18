package com.rcc.dev.backend.constant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 6452167353063968221L;

    @JsonProperty(index = 1)
    private String code;

    @JsonProperty(index = 2)
    private String messageIdn;

    @JsonProperty(index = 3)
    private String messageEng;
}
