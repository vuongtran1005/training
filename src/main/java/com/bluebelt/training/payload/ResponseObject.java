package com.bluebelt.training.payload;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
public class ResponseObject {

    private Boolean success;
    private Object data;

}
