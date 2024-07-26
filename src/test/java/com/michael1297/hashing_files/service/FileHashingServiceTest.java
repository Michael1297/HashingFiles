package com.michael1297.hashing_files.service;

import com.michael1297.hashing_files.dto.FileHash;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FileHashingServiceTest {

    @Autowired
    private FileHashingService fileHashingService;

    @Value("classpath:text.txt")
    private File textFile;

    @Value("classpath:image.png")
    private File imageFile;

    private static final String TEXT_MD5_HASH = "04598740935cc6bb2f31e9ac1bb82661";
    private static final String TEXT_SHA256_HASH = "751d298ccc0880b786d329384dbe56689a7a46ad7b3f39ffab96acc364dd50e2";
    private static final String TEXT_SHA512_HASH = "229ed5145220d81ae14ea8f47441c4fd76532d68be5714633aff5b827768a74ab04b904dd3e8823a2f3049e50b5bbf6eb7ab7f2bd82c3832aa48e6ad8507578c";

    private static final String IMAGE_MD5_HASH = "bcec85c2de4674d736c78b8268b0b01c";
    private static final String IMAGE_SHA256_HASH = "6529e647aedfa5122eb1c7afd598c67cb42a37a734232f04229e5d9748e83ce8";
    private static final String IMAGE_SHA512_HASH = "7b4498672be53c11d0776c6ff8bfe0bc41db264728f406c60530e39572b0ebb691a213a8c3d0a5f3c4ec64afb11f45e8e95fe8efdf515b41bbe2ccb65b4015ee";

    @Test
    public void hashing() throws IOException {
        var textHashResult = fileHashingService.hashing(new MockMultipartFile("text", new FileInputStream(textFile)));
        var imageHashResult = fileHashingService.hashing(new MockMultipartFile("image", new FileInputStream(imageFile)));

        assertEquals(textHashResult.getSecond(), new FileHash(TEXT_MD5_HASH, TEXT_SHA256_HASH, TEXT_SHA512_HASH));
        assertEquals(imageHashResult.getSecond(), new FileHash(IMAGE_MD5_HASH, IMAGE_SHA256_HASH, IMAGE_SHA512_HASH));

        assertFalse(textHashResult.getFirst());
        assertFalse(imageHashResult.getFirst());
    }

    @Test
    public void hashingDuplicate() throws IOException {
        var textHashResult1 = fileHashingService.hashing(new MockMultipartFile("text", new FileInputStream(textFile)));
        var imageHashResult1 = fileHashingService.hashing(new MockMultipartFile("image",  new FileInputStream(imageFile)));

        assertFalse(textHashResult1.getFirst());
        assertFalse(imageHashResult1.getFirst());

        var textHashResult2 = fileHashingService.hashing(new MockMultipartFile("text", new FileInputStream(textFile)));
        var imageHashResult2 = fileHashingService.hashing(new MockMultipartFile("image",  new FileInputStream(imageFile)));

        assertTrue(textHashResult2.getFirst());
        assertTrue(imageHashResult2.getFirst());
    }
}
