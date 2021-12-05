package com.bluebelt.training.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
@Entity
@Table(name = "tbl_product")
public class Product extends BaseEntity{

    @Schema(description = "Product UUID in  the database")

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    @Column(columnDefinition = "TEXT")
    private String description;

    @JsonProperty("seo")
    private String seo;

    @JsonProperty("tags")
    private String tags;

    @ManyToMany(mappedBy = "products")
//    @JsonBackReference
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Không sử dụng trong toString()
    private Set<Collection> collections;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL) // CascadeType.ALL khi lưu product sẽ tự động lưu option
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Không sử dụng trong toString()
    private Set<Option> options;

}
