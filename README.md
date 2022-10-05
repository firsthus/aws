# Global Sprint
## Assignment 2

As **Global Sprint** is turning out to be a **mini-centilytics**. Lets work more towards it. Now, as you've already learned about clouds like AWS and their services like EC2, RDS, S3, IAM etc. we can start working on the APIs and SDKs that AWS provide us.
This is a research heavy assignment and require you to go through documentations, blogs, tutorials to setup project and get going.
***While creating client for EC2, IAM or some other service, study about different types of service clients. You'll be working with [AWSStaticCredentialsProvider](https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/auth/AWSStaticCredentialsProvider.html). Study about different ways of providing credentials [here](https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html).***

1. Go to AWS Console. Generate Access Key and Secret Key for your IAM User.
2. Setup a spring boot project. Add AWS SDK dependency for EC2, S3 and IAM for now.
3. Create an API which will list all the public AMIs and associated data.
   **ENDPOINT:** /security-audit/ec2/public-amis
   **INPUT:**```{"regions": ["ap-south-1","ap-southeast-1","ap-southeast-2","us-east-1"]}```
   **LOGIC:**
    1. Create EC2 Client for all regions in request.
    2. For all regional clients, use ```ec2.describeImages()```. [DescribeImages](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_DescribeImages.html)
    3. This will provide a list of all images.
    4. Now for every image, check if it is public or not. If it is public, add key ```STATUS:CRITICAL``` else add ```STATUS:OK``` in your response.
       **SAMPLE RESPONSE:** ```{"list": [{ "accountId": "3438..", "region": "ap-south-1", "imageId": "", "imageType": "", "creationDate": "", "STATUS": "CRITICAL" }, { "accountId": "3438..", "region": "ap-southeast-1", "imageId": "", "imageType": "", "creationDate": "", "STATUS": "OK" }]}```

4. Create an API which will list all the IAM users and their access rotation status.
   **ENDPOINT:** /security-audit/iam/rotation-check
   **LOGIC:**
    1. Create IAM Client.
    2. With client, use ```iam.getCredentialReport()```. [GetCredentialReport](https://docs.aws.amazon.com/IAM/latest/APIReference/API_GetCredentialReport.html)
    3. This will provide the credential report in csv format which needed to be parsed.
    4. Now for every report, verify ```access_key_1_last_rotated``` and ```access_key_2_last_rotated``` and if time span of rotation is more than 30days add key ```STATUS:CRITICAL``` else add ```STATUS:OK``` in your response.
       **SAMPLE RESPONSE:** ```{"list": [{ "accountId": "3438..", "iamUser": "someName", "keyLastRotated": "2022-08-06 07:07:50", "dayCount": "40", STATUS": "CRITICAL" }, { "accountId": "3438..", "iamUser": "someName2", "keyLastRotated": "2022-09-01 07:07:50", "dayCount": "15", STATUS": "OK" }]}```


***Ask for accessKey and secretKey in case there's any issue generating them. Also read about regional and global services of AWS.***