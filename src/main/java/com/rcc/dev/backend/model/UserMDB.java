package com.rcc.dev.backend.model;

import com.rcc.dev.backend.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class UserMDB {
    @Id
    private String nickName;
    private String fullName;
    private Status status;
}
