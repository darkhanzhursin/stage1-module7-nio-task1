package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;


public class FileReader {
    private String name;
    private Integer age;
    private String email;
    private Long phone;

    public Profile getDataFromFile(File file) {

        try (RandomAccessFile aFile = new RandomAccessFile(file, "r")) {
            String[] nameArr = Files.readAllLines(file.toPath()).get(0).split(":", 2);
            String[] ageArr = Files.readAllLines(file.toPath()).get(1).split(":", 2);
            String[] emailArr = Files.readAllLines(file.toPath()).get(2).split(":", 2);
            String[] phoneArr = Files.readAllLines(file.toPath()).get(3).split(":", 2);

            name = nameArr[1].trim();
            age = Integer.parseInt(ageArr[1].trim());
            email = emailArr[1].trim();
            phone = Long.parseLong(phoneArr[1].trim());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Profile(name, age, email, phone);
    }
}
