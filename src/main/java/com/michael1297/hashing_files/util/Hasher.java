package com.michael1297.hashing_files.util;

import com.google.common.hash.Hashing;
import com.google.common.hash.HashingInputStream;
import com.google.common.io.ByteStreams;
import com.michael1297.hashing_files.dto.FileHash;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Класс содержит методы для вычисления хеша
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Hasher {

    /**
     * Вычисление Md5 хеша для файла
     * @param file файл полученный с веб страницы
     * @return {@link String}
     */
    public static String toMd5(MultipartFile file){
        try (InputStream inputStream = file.getInputStream()) {
            HashingInputStream his = new HashingInputStream(Hashing.md5(), inputStream);
            ByteStreams.copy(his, ByteStreams.nullOutputStream());
            return his.hash().toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Вычисление Sha256 хеша для файла
     * @param file файл полученный с веб страницы
     * @return {@link String}
     */
    public static String toSha256(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            HashingInputStream his = new HashingInputStream(Hashing.sha256(), inputStream);
            ByteStreams.copy(his, ByteStreams.nullOutputStream());
            return his.hash().toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Вычисление  Sha512 хеша для файла
     * @param file файл полученный с веб страницы
     * @return {@link String}
     */
    public static String toSha512(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            HashingInputStream his = new HashingInputStream(Hashing.sha512(), inputStream);
            ByteStreams.copy(his, ByteStreams.nullOutputStream());
            return his.hash().toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Вычисление Md5, Sha256, Sha512 хеша для файла
     * @param file файл полученный с веб страницы
     * @return {@link FileHash}
     */
    @SneakyThrows(InterruptedException.class)
    public static FileHash getFileHash(MultipartFile file) {
        FileHash fileHash = new FileHash();
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(()->{
            fileHash.setMd5(toMd5(file));
        });

        executorService.submit(()->{
            fileHash.setSha256(toSha256(file));
        });

        executorService.submit(()->{
            fileHash.setSha512(toSha512(file));
        });

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);

        return fileHash;
    }
}
