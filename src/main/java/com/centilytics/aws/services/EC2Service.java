package com.centilytics.aws.services;


import com.centilytics.aws.pojos.requests.EC2EnquiryRequest;
import com.centilytics.aws.pojos.responses.AMIDetailsResponse;
import com.centilytics.aws.pojos.responses.APIResponse;

import java.util.List;

public interface EC2Service {

    APIResponse<List<AMIDetailsResponse>> describeImages(EC2EnquiryRequest request);

}
