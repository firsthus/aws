package com.centilytics.aws.services.service_impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;


@Slf4j
@RequiredArgsConstructor
@Service
public class InstanceServiceImpl {

    public String createEC2Instance(Ec2Client ec2, String name, String amiId ) {

        RunInstancesRequest runRequest = RunInstancesRequest.builder()
                .imageId(amiId)
                .instanceType(InstanceType.T2_MICRO)
                .maxCount(1)
                .minCount(1)
                .build();

        log.info("Run Request: {}", runRequest.toString());
        RunInstancesResponse response = ec2.runInstances(runRequest);

        String instanceId = response.instances().get(0).instanceId();
        log.info("Instance ID: {}", instanceId);
        Tag tag1 = Tag.builder()
                .key("Name")
                .value("Festus")
                .build();

        Tag tag2 = Tag.builder()
                .key("Reason")
                .value("Festus Global Pilot")
                .build();

        CreateTagsRequest tagRequest = CreateTagsRequest.builder()
                .resources(instanceId)
                .tags(tag1,tag2)
                .build();

        try {
            CreateTagsResponse tagResponse = ec2.createTags(tagRequest);
            log.info("Tag Response: {}", tagResponse.toString());
            System.out.printf(  "Successfully started EC2 Instance %s based on AMI %s", instanceId, amiId);
            return instanceId;
        } catch (Ec2Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return "Successful";
    }

}