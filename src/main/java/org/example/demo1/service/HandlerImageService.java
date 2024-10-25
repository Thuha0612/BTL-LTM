package org.example.demo1.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class HandlerImageService {
    private static final String SAVE_DIR = "D:/uploaded_images/"; // Đường dẫn lưu ảnh

    public void saveImage(String fileName, byte[] fileData) throws IOException {
        File saveDir = new File(SAVE_DIR);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        File file = new File(SAVE_DIR + fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(fileData);
        }

        System.out.println("File " + fileName + " has been saved.");
    }
}
