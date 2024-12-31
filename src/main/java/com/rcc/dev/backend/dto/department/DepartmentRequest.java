package com.rcc.dev.backend.dto.department;

import jakarta.mail.search.SearchTerm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 4043648503500674335L;

    private Long id;
    private String departmentName;
    private Long userId;
    private Long roleId;
}
