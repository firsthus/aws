package com.centilytics.aws.festusinstance.pojos.request;

import lombok.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.model.InstanceType;

import java.util.List;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class EC2InstanceRequest {

    private Region region;
    private InstanceType instanceType;
    private List<Ec2TagRequest> tagRequestList;
    private String amiId;


    public EC2InstanceRequest() {
        //Adding default values
        this.region = Region.AWS_GLOBAL;
        this.instanceType = InstanceType.T2_MICRO;
    }
}