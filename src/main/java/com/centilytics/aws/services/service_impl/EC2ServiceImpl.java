package com.centilytics.aws.services.service_impl;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.centilytics.aws.enums.SecurityStatus;
import com.centilytics.aws.pojos.requests.EC2EnquiryRequest;
import com.centilytics.aws.pojos.responses.AMIDetailsResponse;
import com.centilytics.aws.pojos.responses.APIResponse;
import com.centilytics.aws.services.EC2Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
public class EC2ServiceImpl implements EC2Service {

    @Override
    public APIResponse<List<AMIDetailsResponse>> describeImages(EC2EnquiryRequest request) {

        List<AMIDetailsResponse> amiDetailsResponses = new ArrayList<>();

        request.getRegions().parallelStream().forEach(region ->{
            try {
                List<AMIDetailsResponse> response = getRegionImages(region);
                amiDetailsResponses.addAll(response);
            } catch (Exception e) {
                log.error("Error while fetching images for region: {}", region, e);
            }
        });

        log.info("Response size: " + amiDetailsResponses.size());

        return APIResponse.<List<AMIDetailsResponse>>builder().success(true).message("AMI Description retrieved").statusCode(200).data(amiDetailsResponses).build();

    }


    private List<AMIDetailsResponse> getRegionImages(String region) {
        List<AMIDetailsResponse> responses = new ArrayList<>();
        AmazonEC2 ec2Client = AmazonEC2ClientBuilder.standard().withRegion(region).build();
        DescribeImagesResult describeImagesResult = ec2Client.describeImages();
        if (describeImagesResult != null && !CollectionUtils.isEmpty(describeImagesResult.getImages())) {
                responses = describeImagesResult.getImages().stream().map(image -> AMIDetailsResponse.builder()
                        .accountId(image.getOwnerId())
                        .region(region)
                        .imageId(image.getImageId())
                        .imageType(image.getImageType())
                        .creationDate(image.getCreationDate())
                        .status(Boolean.TRUE.equals(image.isPublic()) ? SecurityStatus.CRITICAL : SecurityStatus.OK)
                        .build()).collect(Collectors.toList());
        }
        return responses;
    }

}