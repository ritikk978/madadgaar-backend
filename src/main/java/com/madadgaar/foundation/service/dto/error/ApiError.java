package com.madadgaar.foundation.service.dto.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ApiError {

    private final int statusCode;
    private final String message;
}
