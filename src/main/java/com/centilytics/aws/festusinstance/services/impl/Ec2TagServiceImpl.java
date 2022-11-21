package com.centilytics.aws.festusinstance.services.impl;

import com.centilytics.aws.festusinstance.Utils.Ec2Utils;
import com.centilytics.aws.festusinstance.pojos.request.CreateAndAssignEc2TagRequest;
import com.centilytics.aws.festusinstance.pojos.request.Ec2TagRequest;
import com.centilytics.aws.festusinstance.pojos.response.APIResponse;
import com.centilytics.aws.festusinstance.services.Ec2TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.CreateTagsRequest;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;
import software.amazon.awssdk.services.ec2.model.Tag;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
public class Ec2TagServiceImpl implements Ec2TagService {


    @Override
    public APIResponse createEC2Tag(CreateAndAssignEc2TagRequest request) {
        log.debug("Creating EC2 Client");
        try (Ec2Client ec2 = Ec2Utils.createEc2ClientFromRegion(request.getRegion())) {
            return createEC2Tag(ec2, request.getTags(), request.getInstanceId());
        }catch (Ec2Exception e) {
            log.error("An Error occurred: ", e);
            throw new RuntimeException("An error occurred. Try again");
        }
    }


    @Override
    public APIResponse createEC2Tag(Ec2Client ec2Client, List<Ec2TagRequest> tagRequests, String instanceId) {
        log.debug("Creating EC2 Tag");
        CreateTagsRequest tagRequest = CreateTagsRequest.builder()
                .resources(instanceId)
                .tags(tagRequests.stream()
                        .map(tag -> Tag.builder()
                                .key(tag.getKey())
                                .value(tag.getValue())
                                .build())
                        .collect(Collectors.toList()))
                .build();
        ec2Client.createTags(tagRequest);
        log.info("Tags added to instance");
        return APIResponse.builder().success(true).message("Tags successfully created and assigned").build();
    }
}