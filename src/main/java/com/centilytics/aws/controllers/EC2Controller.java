package com.centilytics.aws.controllers;

import com.centilytics.aws.pojos.requests.EC2EnquiryRequest;
import com.centilytics.aws.pojos.responses.AMIDetailsResponse;
import com.centilytics.aws.pojos.responses.APIResponse;
import com.centilytics.aws.services.EC2Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/security-audit/ec2", produces = MediaType.APPLICATION_JSON_VALUE)
public class EC2Controller {

    private final EC2Service ec2Service;


    @PostMapping("/public-amis")
    public ResponseEntity<APIResponse<List<AMIDetailsResponse>>> getPublicAmis(@RequestBody EC2EnquiryRequest request) {
        return ResponseEntity.ok().body(ec2Service.describeImages(request));
    }

}
