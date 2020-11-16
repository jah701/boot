package com.boot.service.csv.impl;


import com.boot.model.User;
import com.boot.repository.RoleRepository;
import com.boot.repository.UserRepository;
import com.boot.service.csv.CsvParserService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileReader;
import java.io.IOException;

@Service
public class CsvParserServiceImpl implements CsvParserService {
    private CSVFormat format;
    private CSVParser csvParser;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public CsvParserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean parseCsvFile(String filePath) throws IOException {
        format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
        csvParser = new CSVParser(new FileReader(filePath), format);

        for (CSVRecord record : csvParser) {
            User user = new User();
            user.setExtended_id(record.get("UserId"));
            user.setPassword("1111");
            user.setName(record.get("ProfileName"));
//            user.setRoles(Set.of(roleRepository.getOne(2L)));

            userRepository.save(user);
        }
        return true;
    }
}

