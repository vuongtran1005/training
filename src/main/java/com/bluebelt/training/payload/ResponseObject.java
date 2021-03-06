package com.bluebelt.training.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@SuperBuilder(toBuilder = true)
public class ResponseObject {

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("data")
    private Object data;

}
