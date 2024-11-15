package com.worktalkie.src.global.storage;

import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.services.s3.model.Bucket;

import java.io.IOException;
import java.util.List;

public interface StorageService {

	List<Bucket> getBuckets();

	void uploadFile(String folderName, MultipartFile file) throws IOException;

	List<String> getFileList(String bucketName);
}
