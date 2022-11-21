package com.centilytics.aws.festusinstance.services;


import com.centilytics.aws.festusinstance.pojos.request.CreateAndAssignEc2TagRequest;
import com.centilytics.aws.festusinstance.pojos.request.Ec2TagRequest;
import com.centilytics.aws.festusinstance.pojos.response.APIResponse;
import software.amazon.awssdk.services.ec2.Ec2Client;

import java.util.List;

public interface Ec2TagService {
    APIResponse createEC2Tag(CreateAndAssignEc2TagRequest tagRequest);

    APIResponse createEC2Tag(Ec2Client ec2Client, List<Ec2TagRequest> tagRequests, String instanceId);
}
