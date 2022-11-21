package com.centilytics.aws.festusinstance.controllers.impl;

import com.centilytics.aws.festusinstance.controllers.Ec2TagController;
import com.centilytics.aws.festusinstance.pojos.request.CreateAndAssignEc2TagRequest;
import com.centilytics.aws.festusinstance.pojos.response.APIResponse;
import com.centilytics.aws.festusinstance.services.Ec2TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Ec2TagControllerImpl implements Ec2TagController {

    private final Ec2TagService ec2TagService;

    @Override
    public ResponseEntity<APIResponse> createEC2Tag(CreateAndAssignEc2TagRequest tagRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ec2TagService.createEC2Tag(tagRequest));
    }
}