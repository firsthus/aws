package com.centilytics.aws.pojos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IAMEnquiryRequest {

    @NotEmpty(message = "regions cannot be empty")
    private List<String> regions;

}