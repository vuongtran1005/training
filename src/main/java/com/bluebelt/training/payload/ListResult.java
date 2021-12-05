package com.bluebelt.training.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
public class ListResult<T> {

    @JsonProperty("_meta")
    public Meta meta;

    @JsonProperty("content")
    public List<T> content;

    public static <T> ListResult<T> from(Page<T> page) {
        return new ListResult<T>()
                .withMeta(new Meta().builder()
                        .page(page.getNumber() + 1)
                        .perPage(page.getSize())
                        .totalElements(page.getTotalElements())
                        .totalPages(page.getTotalPages()).build())
                .withContent(page.getContent());
    }

}
