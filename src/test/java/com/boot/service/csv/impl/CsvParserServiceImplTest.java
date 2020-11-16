package com.boot.service.csv.impl;

import com.boot.repository.RoleRepository;
import com.boot.repository.UserRepository;
import com.boot.service.csv.CsvParserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;

class CsvParserServiceImplTest {
    private final static String WRONG_PATH = "";
    private final static String TEST_PATH = "src/test/java/com/boot/resources/file.csv";

    private static CsvParserService csvParserService;
    private static RoleRepository roleRepository;
    private static UserRepository userRepository;

    @BeforeAll
    static void beforeAll() {
        roleRepository = Mockito.mock(RoleRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        csvParserService = new CsvParserServiceImpl(userRepository, roleRepository);
    }

    @Test
    public void ioexceptionTest() {
        Assertions.assertThrows(IOException.class, () -> csvParserService.parseCsvFile(WRONG_PATH));
    }

    @Test
    public void parseFileTest_Ok() throws IOException {
        Assertions.assertTrue(csvParserService.parseCsvFile(TEST_PATH));
    }
}
