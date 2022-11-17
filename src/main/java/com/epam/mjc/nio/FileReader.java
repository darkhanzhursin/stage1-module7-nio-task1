package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class FileReader {
    private String name;
    private Integer age;
    private String email;
    private Long phone;

    public Profile getDataFromFile(File file) {

        try (RandomAccessFile aFile = new RandomAccessFile(file, "r");
            FileChannel inChannel = aFile.getChannel();) {
            long fileSize = inChannel.size();

            ByteBuffer buffer = ByteBuffer.allocate((int) fileSize);
            inChannel.read(buffer);
            buffer.flip();

            // Now let's convert this ByteBuffer to String
            String converted = new String(buffer.array(), "UTF-8");
            String[] lines = converted.split("\n", 5);

            String[] nameArr = lines[0].split(":",2);
            String[] ageArr = lines[1].split(":", 2);
            String[] emailArr = lines[2].split(":", 2);
            String[] phoneArr = lines[3].split(":", 2);

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
