package com.centilytics.aws.festusinstance.services.impl;

import com.centilytics.aws.festusinstance.Utils.Ec2Utils;
import com.centilytics.aws.festusinstance.pojos.request.EC2InstanceRequest;
import com.centilytics.aws.festusinstance.pojos.response.APIResponse;
import com.centilytics.aws.festusinstance.services.Ec2InstanceService;
import com.centilytics.aws.festusinstance.services.Ec2TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;
import software.amazon.awssdk.services.ec2.model.InstanceType;
import software.amazon.awssdk.services.ec2.model.RunInstancesRequest;
import software.amazon.awssdk.services.ec2.model.RunInstancesResponse;


@Slf4j
@RequiredArgsConstructor
@Service
public class Ec2InstanceServiceImpl implements Ec2InstanceService {

    private final Ec2TagService ec2TagService;


    @Override
    public APIResponse getSupportedEc2InstanceTypes() {
        return APIResponse.builder()
                .success(true)
                .message("Instance types successfully fetched")
                .data(InstanceType.knownValues())
                .build();
    }


    @Override
    public APIResponse createEC2Instance(EC2InstanceRequest ec2Request) {

        log.info("Creating EC2 instance");

        try (Ec2Client ec2 = Ec2Utils.createEc2ClientFromRegion(ec2Request.getRegion())) {

            RunInstancesRequest runRequest = RunInstancesRequest.builder()
                    .imageId(ec2Request.getAmiId())
                    .instanceType(ec2Request.getInstanceType())
                    .maxCount(1)
                    .minCount(1)
                    .build();
            RunInstancesResponse response = ec2.runInstances(runRequest);
            String instanceId = response.instances().get(0).instanceId();
            log.info("Instance created with instanceId: {}", instanceId);

            if(!CollectionUtils.isEmpty(ec2Request.getTagRequestList())) {
                ec2TagService.createEC2Tag(ec2, ec2Request.getTagRequestList(), instanceId);
                log.info("Tags added to instance");
            }
            return APIResponse.builder()
                    .success(true)
                    .message(String.format("Instance Successfully Created with instanceId: %s", instanceId))
                    .build();
        }catch (Ec2Exception e) {
            log.error("Error while creating EC2 instance: ", e);
            throw new RuntimeException("Error while creating EC2 instance");
        }

    }

}