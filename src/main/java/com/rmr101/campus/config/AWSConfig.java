package com.rmr101.campus.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;



@PropertySource(ignoreResourceNotFound = true, value = {
    "classpath:awsBackup.properties",
    "classpath:aws.properties"})
@ConfigurationProperties(prefix="s3")
@Data
@Component
public class AWSConfig {
    private String secretKey;
    private String accessKey;
}
