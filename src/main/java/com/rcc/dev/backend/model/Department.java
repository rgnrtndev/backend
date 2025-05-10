package com.rcc.dev.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "department")
public class Department extends BaseDomain implements Serializable {
    @Serial
    private static final long serialVersionUID = 2382692392113362964L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "department_name", nullable = false)
    private String departmentName;

    @OneToOne
    @JoinColumn(name = "department_role_id", referencedColumnName = "id")
    @JsonManagedReference
    private DepartmentRole departmentRole;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<MapUserDepartment> departmentUsers;
}

