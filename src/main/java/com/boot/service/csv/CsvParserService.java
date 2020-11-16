package com.boot.service.csv;

import java.io.IOException;

public interface CsvParserService {
    boolean parseCsvFile(String filePath) throws IOException;
}
