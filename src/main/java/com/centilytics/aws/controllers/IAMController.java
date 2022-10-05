package com.centilytics.aws.controllers;

import com.centilytics.aws.pojos.responses.IAMResponse;
import com.centilytics.aws.services.IAMService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/security-audit/iam", produces = MediaType.APPLICATION_JSON_VALUE)
public class IAMController {

    private final IAMService iamService;


    @GetMapping("/rotation-check")
    public ResponseEntity<List<IAMResponse>> getPublicAmis() {
        return ResponseEntity.ok().body(iamService.getClientReport());
    }

}
