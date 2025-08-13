package com.BillingApp.util;

//util/FileUploadUtil.java

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.UUID;

public class FileUploadUtil {

    /**
     * Saves the given file to the specified upload directory.
     *
     * @param uploadDir    the directory path to save the file (e.g., "uploads/invoices/")
     * @param fileName     the name to save the file as
     * @param multipartFile the multipart file uploaded from client
     * @return the full path of the saved file
     * @throws IOException if file writing fails
     */
    public static String saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        } catch (IOException ex) {
            throw new IOException("Could not save file: " + fileName, ex);
        }
    }

    /**
     * Generates a unique filename using UUID and original extension.
     *
     * @param originalFilename original filename
     * @return unique filename with original extension
     */
    public static String generateUniqueFileName(String originalFilename) {
        String extension = "";
        int dotIndex = originalFilename.lastIndexOf('.');
        if (dotIndex > 0) {
            extension = originalFilename.substring(dotIndex);
        }
        return UUID.randomUUID().toString() + extension;
    }
}
