package com.worktalkie.src.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class NcloudStorageService implements StorageService {
    private final NcloudStorage ncloudStorage;

    @Value("${cloud.ncloud.bucket}")
    private String bucketName;

    public NcloudStorageService(NcloudStorage ncloudStorage) {
        this.ncloudStorage = ncloudStorage;
    }

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        ncloudStorage.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), null));
        return ncloudStorage.getUrl(bucketName, fileName).toString();
    }
}
