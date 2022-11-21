package com.centilytics.aws.festusinstance.controllers;

import com.centilytics.aws.festusinstance.pojos.request.CreateAndAssignEc2TagRequest;
import com.centilytics.aws.festusinstance.pojos.response.APIResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(value = "/ec2-tags", produces = MediaType.APPLICATION_JSON_VALUE)
public interface Ec2TagController {

    @PostMapping("/create-and-assign")
    ResponseEntity<APIResponse> createEC2Tag(@Valid @RequestBody CreateAndAssignEc2TagRequest tagRequest);

}