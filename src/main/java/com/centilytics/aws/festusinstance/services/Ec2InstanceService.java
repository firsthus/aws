package com.centilytics.aws.festusinstance.services;


import com.centilytics.aws.festusinstance.pojos.request.EC2InstanceRequest;
import com.centilytics.aws.festusinstance.pojos.response.APIResponse;

public interface Ec2InstanceService {
    APIResponse createEC2Instance(EC2InstanceRequest ec2Request);

    APIResponse getSupportedEc2InstanceTypes();
}
