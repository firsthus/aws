package com.centilytics.aws.festusinstance.pojos.request;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ec2TagRequest {

    @NotBlank
    private String key;

    @NotBlank
    private String value;
}