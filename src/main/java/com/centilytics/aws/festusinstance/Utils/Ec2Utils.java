package com.centilytics.aws.festusinstance.Utils;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;

public class Ec2Utils {

    public static Ec2Client createEc2ClientFromRegion(Region region) {
        return Ec2Client.builder().region(region)
                .credentialsProvider(DefaultCredentialsProvider.create()).build();
    }
}
