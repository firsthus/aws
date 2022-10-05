package com.centilytics.aws.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@RequiredArgsConstructor
public class ValidationError {

    private final String fieldName;

    private final String message;

}
