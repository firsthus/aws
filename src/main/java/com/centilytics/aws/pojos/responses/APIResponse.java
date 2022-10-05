package com.centilytics.aws.pojos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class APIResponse<T> {

    private boolean success;

    private int statusCode;

    private String message;

    private T data;

}
