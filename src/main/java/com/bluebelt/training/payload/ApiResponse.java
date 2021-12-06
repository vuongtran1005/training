package com.bluebelt.training.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class ApiResponse implements Serializable {

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("message")
    private String message;

}
