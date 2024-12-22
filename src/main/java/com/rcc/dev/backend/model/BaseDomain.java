package com.rcc.dev.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseDomain implements Serializable{

    @Serial
    private static final long serialVersionUID = 1149264352632570435L;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

    @Column(name = "CREATED_BY", nullable = true)
    private Long createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "MODIFIED_DATE", nullable = true)
    private Date modifiedDate;

    @Column(name = "MODIFIED_BY", nullable = true)
    private Long modifiedBy;

    @PrePersist
    public void onCreate(){
        if (null == this.createdDate){
            this.createdDate = new Date();
        }
    }

    @PreUpdate
    public void onUpdate(){
        if (null == this.modifiedDate){
            this.modifiedDate = new Date();
        }
    }
}
