package com.centilytics.aws.festusinstance.controllers;

import com.centilytics.aws.festusinstance.pojos.response.APIResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/aws-regions", produces = MediaType.APPLICATION_JSON_VALUE)
public interface AwsRegionController {

    @GetMapping("/all")
    ResponseEntity<APIResponse> getAllRegion();

}