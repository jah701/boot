package com.boot.service.csv.impl;

import com.boot.service.csv.CsvLoaderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CsvLoaderServiceTest {
    private String WRONG_URL = "";
    private String WRONG_PATH = "se/cer/c/e.txt";
    private String CORRECT_URL = "https://spring-boot-aws-revievers.s3.eu-central-1.amazonaws.com/Reviews.csv";
    private String CORRECT_PATH = "src/test/java/com/boot/resources/empty-file.csv";
    private String CORRECT_PATH2 = "src/test/java/com/boot/resources/test-file.csv";
    private static CsvLoaderService csvLoaderService;

    @BeforeAll
    static void beforeAll() {
        csvLoaderService = new CsvLoaderServiceImpl();
    }

    @Test
    public void loadFromWrongUrl() {
        Assertions.assertThrows(RuntimeException.class, () -> csvLoaderService.loadCsvFile(WRONG_URL, WRONG_PATH));
    }

    @Test
    public void loadFromCorrectPath() {
        boolean result = csvLoaderService.loadCsvFile(CORRECT_URL, CORRECT_PATH2);
        Assertions.assertEquals(true, result);
    }

}