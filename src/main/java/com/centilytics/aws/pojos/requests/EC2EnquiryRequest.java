package com.centilytics.aws.pojos.requests;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EC2EnquiryRequest {

    @NotEmpty(message = "regions cannot be empty")
    private List<String> regions;

}