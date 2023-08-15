package com.madadgaar.foundation.model.response;

import com.madadgaar.foundation.service.dto.response.ResponseDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse implements ResponseDTO {

    private String message;

    public static ErrorResponse of(String error){
        return ErrorResponse.builder()
                .message(error)
                .build();
    }
}
