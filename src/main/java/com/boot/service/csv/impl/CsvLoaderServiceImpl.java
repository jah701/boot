package com.boot.service.csv.impl;

import com.boot.service.csv.CsvLoaderService;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import org.springframework.stereotype.Service;

@Service
public class CsvLoaderServiceImpl implements CsvLoaderService {
    @Override
    public boolean loadCsvFile(String url, String intoPath) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(intoPath)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't load CSV file from " + url, e);
        }
        return true;
    }
}
