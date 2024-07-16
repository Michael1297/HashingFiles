package com.michael1297.hashing_files.service;

import com.michael1297.hashing_files.dto.FileHash;
import com.michael1297.hashing_files.dto.Pair;
import org.springframework.web.multipart.MultipartFile;

public interface FileHashingService {
    Pair<Boolean, FileHash> hashing(MultipartFile file);
}
