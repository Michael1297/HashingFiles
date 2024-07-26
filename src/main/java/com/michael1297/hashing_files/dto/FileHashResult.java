package com.michael1297.hashing_files.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Используется для передачи результата хеширования из сервиса в контроллер
 */
@Getter
@Setter
public class FileHashResult {
    private FileHash fileHash;
    private boolean hashed;
}
