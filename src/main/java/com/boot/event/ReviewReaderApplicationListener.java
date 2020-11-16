package com.boot.event;

import com.boot.model.Role;
import com.boot.service.RoleService;
import com.boot.service.csv.CsvLoaderService;
import com.boot.service.csv.CsvParserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Log4j
@Component
public class ReviewReaderApplicationListener implements ApplicationListener<ApplicationReadyEvent> {
    @Value("${csv.file.url}")
    private String csvUrl;
    @Value("${load.into.path}")
    private String loadedFile;
    @Value("${parse.into.path}")
    private String parsedFile;

    private final RoleService roleService;
    private final CsvParserService csvParserService;
    private final CsvLoaderService csvLoaderService;

    public ReviewReaderApplicationListener(CsvLoaderService csvLoaderService, CsvParserService csvParserService, RoleService roleService) {
        this.csvLoaderService = csvLoaderService;
        this.csvParserService = csvParserService;
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.info("Injecting data . . .");
        Role admin = Role.of("ADMIN");
        Role user = Role.of("USER");
        roleService.add(admin);
        roleService.add(user);
        log.info("Data injected!");

        log.info("ReviewReaderApplicationListener onApplicationEvent() started . . .");
        try {
            csvLoaderService.loadCsvFile(csvUrl, loadedFile);
            csvParserService.parseCsvFile(loadedFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed while parsing CSV file " + loadedFile);
        }
        log.info("ReviewReaderApplicationListener onApplicationEvent() finished successfully");
    }
}
