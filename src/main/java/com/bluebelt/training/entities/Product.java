package com.bluebelt.training.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
@Entity
@Table(name = "tbl_product")
public class Product extends BaseEntity{

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @JsonProperty("import_price")
    private BigDecimal importPrice;

}
