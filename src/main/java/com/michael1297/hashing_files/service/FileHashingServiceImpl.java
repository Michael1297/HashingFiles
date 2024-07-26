package com.michael1297.hashing_files.service;


import com.michael1297.hashing_files.dto.FileHash;
import com.michael1297.hashing_files.dto.FileHashResult;
import com.michael1297.hashing_files.util.Hasher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@Service
public class FileHashingServiceImpl implements FileHashingService {

    Set<FileHash> fileHashes = new HashSet<>();

    /**
     * Вычисление хешей для файла
     * @param file файл
     * @return {@link FileHashResult} используется для вывода результата хеширования
     */
    @Override
    public FileHashResult hashing(MultipartFile file) {
        FileHash fileHash = Hasher.getFileHash(file);

        FileHashResult result = new FileHashResult();
        result.setFileHash(fileHash);
        result.setHashed(fileHashes.contains(fileHash));
        fileHashes.add(fileHash);

        return result;
    }
}
