package com.centilytics.aws.pojos.responses;

import com.centilytics.aws.enums.SecurityStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IAMResponse{

    private String accountId;
    private String iamUser;
    private Date keyLastRotated;
    private Long dayCount;
    private SecurityStatus status;

}