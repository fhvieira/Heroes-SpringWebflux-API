package com.digitalinnovationone.heroesapi.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@EnableDynamoDBRepositories
public class DynamoConfig {
    @Value("${amazom.dynamodb.endpoint}")
    private String amazomDynamoDBEndpoint;

    @Value("${aws_access_key_id}")
    private String amazomAWSAccessKey;

    @Value("${aws_secret_access_key}")
    private String amazomAWSSecretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials());

        if (!StringUtils.isEmpty(amazomDynamoDBEndpoint)) {
            amazonDynamoDB.setEndpoint(amazomDynamoDBEndpoint);
        }
        return amazonDynamoDB;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(
                amazomAWSAccessKey,
                amazomAWSSecretKey);
    }

}