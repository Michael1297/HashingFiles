package com.michael1297.hashing_files.service;


import com.michael1297.hashing_files.dto.FileHash;
import com.michael1297.hashing_files.dto.Pair;
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
     * @return {@link Pair} где в качестве ключа используется {@link Boolean} для проверки, что файл уже был хэширован,
     * а в качестве значения используется {@link FileHash} хеш файла
     */
    @Override
    public Pair<Boolean, FileHash> hashing(MultipartFile file) {
        FileHash fileHash = Hasher.getFileHash(file);

        if(fileHashes.contains(fileHash)) {
            return new Pair<>(true, fileHash);
        } else {
            fileHashes.add(fileHash);
            return new Pair<>(false, fileHash);
        }

    }
}
