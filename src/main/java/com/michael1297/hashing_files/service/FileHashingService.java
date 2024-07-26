package com.michael1297.hashing_files.service;

import com.michael1297.hashing_files.dto.FileHashResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileHashingService {
    FileHashResult hashing(MultipartFile file);
}
