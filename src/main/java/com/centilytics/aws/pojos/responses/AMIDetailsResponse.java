package com.centilytics.aws.pojos.responses;

import com.centilytics.aws.enums.SecurityStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AMIDetailsResponse {

    private String accountId;
    private String region;
    private String imageId;
    private String imageType;
    private String creationDate;
    private SecurityStatus status;

}