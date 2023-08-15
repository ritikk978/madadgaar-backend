package com.madadgaar.foundation.service.util;
import com.madadgaar.foundation.service.dto.error.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static com.madadgaar.foundation.controller.util.ApiUtils.createErrorResponse;

@Slf4j
public class AppUtils {

    public static ResponseEntity createErrorResponseEntity(String message) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), message);
        Map<String, ApiError> errorResponse = createErrorResponse(apiError);

        return ResponseEntity.badRequest().body(errorResponse);
    }

}