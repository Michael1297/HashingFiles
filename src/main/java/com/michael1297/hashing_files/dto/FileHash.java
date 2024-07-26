package com.michael1297.hashing_files.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Используется для хранения Md5, Sha256, Sha512 хеша
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileHash {
    private String md5;
    private String sha256;
    private String sha512;
}
