package com.bluebelt.training.entities;

import com.bluebelt.training.entities.common.EBoolean;
import com.bluebelt.training.entities.common.EStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class) // lắng nghe
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreatedDate
    @JsonProperty("created_at")
    private Date createdAt;

    @LastModifiedDate
    @JsonProperty("updated_at")
    private Date updatedAt;

    @CreatedBy
    @JsonProperty("created_by")
    private String createdBy;

    @LastModifiedBy
    @JsonProperty("updated_by")
    private String updatedBy;

    @Enumerated(EnumType.STRING)
    private EStatus status = EStatus.ACTIVE;

    @JsonProperty("is_deleted")
    @Enumerated(EnumType.STRING)
    private EBoolean isDeleted = EBoolean.FALSE;

}
