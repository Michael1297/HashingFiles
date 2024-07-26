package com.michael1297.hashing_files.util;

import com.michael1297.hashing_files.dto.FileHash;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class HasherTest {

    @Value("classpath:text.txt")
    private InputStream textFile;

    @Value("classpath:image.png")
    private InputStream imageFile;

    private static final String TEXT_MD5_HASH = "04598740935cc6bb2f31e9ac1bb82661";
    private static final String TEXT_SHA256_HASH = "751d298ccc0880b786d329384dbe56689a7a46ad7b3f39ffab96acc364dd50e2";
    private static final String TEXT_SHA512_HASH = "229ed5145220d81ae14ea8f47441c4fd76532d68be5714633aff5b827768a74ab04b904dd3e8823a2f3049e50b5bbf6eb7ab7f2bd82c3832aa48e6ad8507578c";

    private static final String IMAGE_MD5_HASH = "bcec85c2de4674d736c78b8268b0b01c";
    private static final String IMAGE_SHA256_HASH = "6529e647aedfa5122eb1c7afd598c67cb42a37a734232f04229e5d9748e83ce8";
    private static final String IMAGE_SHA512_HASH = "7b4498672be53c11d0776c6ff8bfe0bc41db264728f406c60530e39572b0ebb691a213a8c3d0a5f3c4ec64afb11f45e8e95fe8efdf515b41bbe2ccb65b4015ee";

    @Test
    public void toMd5() throws IOException {
        String textResult = Hasher.toMd5(new MockMultipartFile("text", textFile));
        String imageResult = Hasher.toMd5(new MockMultipartFile("image", imageFile));

        assertEquals(textResult, TEXT_MD5_HASH);
        assertEquals(imageResult, IMAGE_MD5_HASH);
    }

    @Test
    public void toSha256() throws IOException {
        String textResult = Hasher.toSha256(new MockMultipartFile("text", textFile));
        String imageResult = Hasher.toSha256(new MockMultipartFile("image", imageFile));

        assertEquals(textResult, TEXT_SHA256_HASH);
        assertEquals(imageResult, IMAGE_SHA256_HASH);
    }

    @Test
    public void toSha512() throws IOException {
        String textResult = Hasher.toSha512(new MockMultipartFile("text", textFile));
        String imageResult = Hasher.toSha512(new MockMultipartFile("image", imageFile));

        assertEquals(textResult, TEXT_SHA512_HASH);
        assertEquals(imageResult, IMAGE_SHA512_HASH);
    }

    @Test
    public void getFileHash() throws IOException {
        FileHash textHash = Hasher.getFileHash(new MockMultipartFile("text", textFile));
        FileHash imageHash = Hasher.getFileHash(new MockMultipartFile("image", imageFile));

        assertEquals(textHash, new FileHash(TEXT_MD5_HASH, TEXT_SHA256_HASH, TEXT_SHA512_HASH));
        assertEquals(imageHash, new FileHash(IMAGE_MD5_HASH, IMAGE_SHA256_HASH, IMAGE_SHA512_HASH));
    }
}
