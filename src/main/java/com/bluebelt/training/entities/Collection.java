package com.bluebelt.training.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
@Entity
@Table(name = "tbl_collection")
public class Collection extends BaseEntity{

    private String title;

    @JsonProperty("sort_order")
    private String sortOrder;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // LAZY để tránh việc truy xuất dữ liệu không cần thiết. Lúc nào cần thì mới query
    @JoinTable(name = "tbl_collection_product",
    joinColumns = @JoinColumn(name = "collection_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    @JsonManagedReference
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Không sử dụng trong toString()
    private Set<Product> products;

}
