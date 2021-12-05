package com.bluebelt.training.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("tags")
    private String tags;

    @JsonProperty("options_id")
    private Set<Integer> optionsId;

    @JsonProperty("collections_id")
    private Set<Integer> collectionsId;

    public Set<Integer> getOptionsId(){
        return this.optionsId == null ? Collections.emptySet() : new HashSet<>(optionsId);
    }

    public Set<Integer> getCollectionsId(){
        return this.collectionsId == null ? Collections.emptySet() : new HashSet<>(collectionsId);
    }

}
