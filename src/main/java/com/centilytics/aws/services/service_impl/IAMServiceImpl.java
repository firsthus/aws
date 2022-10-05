package com.centilytics.aws.services.service_impl;

import com.centilytics.aws.enums.SecurityStatus;
import com.centilytics.aws.pojos.responses.IAMResponse;
import com.centilytics.aws.services.IAMService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;

import java.io.StringReader;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.centilytics.aws.utils.Constants.MAXIMUM_ALLOWED_SPAN_OF_ROTATION;

@Slf4j
@RequiredArgsConstructor
@Service
public class IAMServiceImpl implements IAMService {


    @Override
    public List<IAMResponse> getClientReport() {
        List<IAMResponse> responses = new ArrayList<>();
            Region awsRegion = Region.AWS_GLOBAL;
            try (IamClient iamClient = IamClient.builder().region(awsRegion).credentialsProvider(EnvironmentVariableCredentialsProvider.create()).build()) {
                iamClient.generateCredentialReport();
                String csvReport = iamClient.getCredentialReport().content().asUtf8String();
                CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim();
                CSVParser csvParser = new CSVParser(new StringReader(csvReport), csvFormat);
                csvParser.getRecords().forEach(csvRecord -> {
                    IAMResponse userInfo = new IAMResponse();
                    userInfo.setIamUser(csvRecord.get("user"));
                    if(csvRecord.get("access_key_1_active").equals("true")){
                        ZonedDateTime lastRotated = ZonedDateTime.parse(csvRecord.get("access_key_1_last_rotated"));
                        long dayCount = Duration.between(lastRotated, ZonedDateTime.now()).toDays();

                        if(dayCount > MAXIMUM_ALLOWED_SPAN_OF_ROTATION) userInfo.setStatus(SecurityStatus.CRITICAL);
                        else userInfo.setStatus(SecurityStatus.OK);

                        userInfo.setKeyLastRotated(Date.from(lastRotated.toInstant()));
                        userInfo.setDayCount(dayCount);
                    }

                    if(csvRecord.get("access_key_2_active").equals("true")) {
                        ZonedDateTime lastRotated = ZonedDateTime.parse(csvRecord.get("access_key_2_last_rotated"));
                        long dayCount = Duration.between(lastRotated, ZonedDateTime.now()).toDays();
                        if (dayCount > MAXIMUM_ALLOWED_SPAN_OF_ROTATION) {
                            userInfo.setStatus(SecurityStatus.CRITICAL);
                            userInfo.setKeyLastRotated(Date.from(lastRotated.toInstant()));
                            userInfo.setDayCount(dayCount);
                        }
                    }
                    responses.add(userInfo);
                });
            } catch (Exception e) {
                log.info("Error while getting credential report", e);
            }
            return responses;
    }



}