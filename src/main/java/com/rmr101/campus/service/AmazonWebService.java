package com.rmr101.campus.service;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.rmr101.campus.config.AWSConfig;
import com.rmr101.campus.dto.studentAssignment.StudentAssignmentS3Url;
import com.rmr101.campus.dto.studentAssignment.StudentAssignmentStudentPutRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.URL;



//Tutorial link: https://docs.aws.amazon.com/AmazonS3/latest/dev/PresignedUrlUploadObjectJavaSDK.html

@Service
public class AmazonWebService {

    @Autowired
    StudentAssignmentService studentAssignmentService;

    @Autowired
    AWSConfig awsConfig;

    private Regions clientRegion = Regions.AP_SOUTHEAST_2;
    // Fix this two for now:
    private String bucketName = "campus-file-system";
    private String objectKeySubFolder = "assignment/";

    public StudentAssignmentS3Url preSignedUploadedUrl(Long assignmentId, String fileName) throws SdkClientException {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials( awsConfig.getAccessKey(),awsConfig.getSecretKey());
        String objectKey = objectKeySubFolder+fileName;
        StudentAssignmentStudentPutRequest request = new StudentAssignmentStudentPutRequest();
        request.setAttachmentUrl(objectKey);
        studentAssignmentService.submitAssignment(assignmentId, request);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
            .withRegion(clientRegion)
            .build();

        // Set the pre-signed URL to expire after 10 mins.
        java.util.Date expiration = new java.util.Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 10;
        expiration.setTime(expTimeMillis);

        // Generate the pre-signed URL.
        System.out.println("Generating pre-signed URL.");
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(
            bucketName, objectKey)
            .withMethod(HttpMethod.PUT)
            .withExpiration(expiration);
        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        StudentAssignmentS3Url S3Url = new StudentAssignmentS3Url();
        S3Url.setUrl(url.toString());
        S3Url.setFileName(fileName);
        S3Url.setObjectKey(objectKey);
        return S3Url;
    }
    public StudentAssignmentS3Url preSignedGetUrl(String objectKey) throws SdkClientException{
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials( awsConfig.getAccessKey(),awsConfig.getSecretKey());
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
            .withRegion(clientRegion)
            .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
            .build();

        // Set the presigned URL to expire after one hour.
        java.util.Date expiration = new java.util.Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 10;
        expiration.setTime(expTimeMillis);

        // Generate the presigned URL.
        System.out.println("Generating pre-signed URL.");
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
            new GeneratePresignedUrlRequest(bucketName, objectKey)
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration);
        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        StudentAssignmentS3Url S3Url = new StudentAssignmentS3Url();
        S3Url.setUrl(url.toString());
        S3Url.setObjectKey(objectKey);
        return S3Url;
    }
}
