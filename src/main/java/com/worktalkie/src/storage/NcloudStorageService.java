package com.worktalkie.src.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;


import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NcloudStorageService implements StorageService {
    private final String endPoint = "1234";
    private final String regionName = "1";
    private final String accessKey = "ACCESS_KEY";
    private final String secretKey = "SECRET_KEY";
    private final S3Client s3 = S3Client.builder()
            .endpointOverride(URI.create(endPoint))
            .region(Region.of(regionName))
            .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
            .build();

    @Value("${cloud.ncloud.bucket}")
    private String bucketName;

    @Override
    public List<Bucket> getBuckets() {
        return s3.listBuckets().buckets();
    }

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        return "";
    }

    @Override
    public void storeAudio(String chatRoomId, MultipartFile audio) {

    }

    @Override
    public List<String> getFileList(String bucketName) {
        ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .build();

        SdkIterable<ListObjectsV2Response> responses = s3.listObjectsV2Paginator(listObjectsRequest);
        return StreamSupport.stream(responses.spliterator(), false)
                .flatMap(response -> response.contents().stream())
                .map(S3Object::key)  // Get the key (object name)
                .toList();
    }
}
