package com.bluebelt.training.exceptions;

import com.bluebelt.training.payload.ApiResponse;
import com.bluebelt.training.payload.ResponseObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceNotFoundException extends RuntimeException{

    private transient ApiResponse apiResponse;

    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ApiResponse getApiResponse() {
        String message = String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue);

        return new ApiResponse(Boolean.FALSE, message);
    }

}
