package com.centilytics.aws.services;


import com.centilytics.aws.pojos.responses.IAMResponse;

import java.util.List;

public interface IAMService {

    List<IAMResponse> getClientReport();
}
