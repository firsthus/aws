package com.centilytics.aws.festusinstance.services.impl;

import com.centilytics.aws.festusinstance.pojos.response.APIResponse;
import com.centilytics.aws.festusinstance.services.AwsRegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;


@Slf4j
@RequiredArgsConstructor
@Service
public class AwsRegionServiceImpl implements AwsRegionService {

    @Override
    public APIResponse getAllAwsRegion() {
        return APIResponse.builder()
                .success(true)
                .message("Regions successfully fetched")
                .data(Region.regions().stream().map(Region::toString).toArray())
                .build();
    }
}