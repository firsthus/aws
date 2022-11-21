package com.centilytics.aws.festusinstance.pojos.request;

import lombok.*;
import software.amazon.awssdk.regions.Region;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAndAssignEc2TagRequest {

    @NotEmpty
    private List<Ec2TagRequest> tags;

    @NotBlank
    private String instanceId;

    @NotNull
    private Region region;

}