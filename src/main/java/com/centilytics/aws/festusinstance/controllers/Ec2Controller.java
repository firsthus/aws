package com.centilytics.aws.festusinstance.controllers;

import com.centilytics.aws.festusinstance.pojos.request.EC2InstanceRequest;
import com.centilytics.aws.festusinstance.pojos.response.APIResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/ec2", produces = MediaType.APPLICATION_JSON_VALUE)
public interface Ec2Controller {

    @PostMapping("/create")
    ResponseEntity<APIResponse> createEc2Instance(@Valid @RequestBody EC2InstanceRequest request);

    @GetMapping("/instance-types")
    ResponseEntity<APIResponse> getSupportedEc2InstanceTypes();
}