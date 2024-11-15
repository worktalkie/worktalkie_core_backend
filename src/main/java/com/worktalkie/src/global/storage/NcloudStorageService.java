package com.worktalkie.src.global.storage;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.StreamSupport;

// @Slf4j
// @Service
// public class NcloudStorageService implements StorageService {
// 	@Value("${cloud.ncloud.endpoint}")
// 	private String endPoint;
//
// 	@Value("${cloud.ncloud.region}")
// 	private String regionName;
//
// 	@Value("${cloud.ncloud.access-key}")
// 	private String accessKey;
//
// 	@Value("${cloud.ncloud.secret-key}")
// 	private String secretKey;
//
// 	@Value("${cloud.ncloud.bucket}")
// 	private String bucketName;
//
// 	private S3Client s3;
//
// 	@PostConstruct
// 	public void init() {
// 		this.s3 = S3Client.builder()
// 			.endpointOverride(URI.create(endPoint))
// 			.region(Region.of(regionName))
// 			.credentialsProvider(StaticCredentialsProvider.create(
// 				AwsBasicCredentials.create(accessKey, secretKey)
// 			))
// 			.build();
// 	}
//
// 	@Override
// 	public List<Bucket> getBuckets() {
// 		return s3.listBuckets().buckets();
// 	}
//
// 	@Override
// 	public void uploadFile(String chatRoomId, MultipartFile file) throws IOException {
// 		String folderPath = "audio/" + chatRoomId;
// 		String filePath = folderPath + "_" + file.getOriginalFilename();
// 		PutObjectRequest putObjectRequest = PutObjectRequest.builder()
// 			.bucket(bucketName)
// 			.key(filePath)
// 			.contentType(file.getContentType())
// 			.build();
//
// 		log.info("Uploading file: {}", filePath);
// 		s3.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
// 	}
//
// 	@Override
// 	public List<String> getFileList(String bucketName) {
// 		ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
// 			.bucket(bucketName)
// 			.build();
//
// 		SdkIterable<ListObjectsV2Response> responses = s3.listObjectsV2Paginator(listObjectsRequest);
// 		return StreamSupport.stream(responses.spliterator(), false)
// 			.flatMap(response -> response.contents().stream())
// 			.map(S3Object::key)  // Get the key (object name)
// 			.toList();
// 	}
// }
