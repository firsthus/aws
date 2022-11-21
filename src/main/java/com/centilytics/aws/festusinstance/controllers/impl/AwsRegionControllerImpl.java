package com.centilytics.aws.festusinstance.controllers.impl;

import com.centilytics.aws.festusinstance.controllers.AwsRegionController;
import com.centilytics.aws.festusinstance.pojos.response.APIResponse;
import com.centilytics.aws.festusinstance.services.AwsRegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AwsRegionControllerImpl implements AwsRegionController {

    private final AwsRegionService awsRegionService;

    @Override
    public ResponseEntity<APIResponse> getAllRegion() {
        return ResponseEntity.ok(awsRegionService.getAllAwsRegion());
    }
}