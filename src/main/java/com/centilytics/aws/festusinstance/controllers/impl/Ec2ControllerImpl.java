package com.centilytics.aws.festusinstance.controllers.impl;

import com.centilytics.aws.festusinstance.controllers.Ec2Controller;
import com.centilytics.aws.festusinstance.pojos.request.EC2InstanceRequest;
import com.centilytics.aws.festusinstance.pojos.response.APIResponse;
import com.centilytics.aws.festusinstance.services.Ec2InstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Ec2ControllerImpl implements Ec2Controller {

    private final Ec2InstanceService ec2InstanceService;


    @Override
    public ResponseEntity<APIResponse> getSupportedEc2InstanceTypes() {
        return ResponseEntity.ok(ec2InstanceService.getSupportedEc2InstanceTypes());
    }

    @Override
    public ResponseEntity<APIResponse> createEc2Instance(EC2InstanceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ec2InstanceService.createEC2Instance(request));
    }

}