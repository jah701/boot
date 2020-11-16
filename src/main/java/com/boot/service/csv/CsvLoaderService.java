package com.boot.service.csv;

public interface CsvLoaderService {
    boolean loadCsvFile(String url, String intoPath);
}
