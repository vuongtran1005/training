package com.bluebelt.training.entities;

import com.bluebelt.training.entities.common.EBoolean;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tbl_product_variant")
public class ProductVariant extends BaseEntity{

    @JsonProperty("bar_code")
    private String barCode;

    @JsonProperty("sku")
    private String sku;

    @JsonProperty("taxable")
    @Enumerated(EnumType.STRING)
    private EBoolean taxable = EBoolean.FALSE;

    @JsonProperty("tax_code")
    private String taxCode;

    @JsonProperty("title")
    private String title;

    @JsonProperty("import_price")
    private BigDecimal importPrice;

    @JsonProperty("export_price")
    private BigDecimal exportPrice;

    @JsonProperty("sale_price")
    private BigDecimal salePrice;

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Option option;

}
