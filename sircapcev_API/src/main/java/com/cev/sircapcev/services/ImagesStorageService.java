package com.cev.sircapcev.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class ImagesStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeImage(MultipartFile file) throws IOException {
        try {
            if (file.isEmpty())
                throw new IOException("No se pudo guardar el archivo vacío");            
            
            String originalFileName = file.getOriginalFilename();
            String uniqueFileName = generateUniqueFileName(originalFileName);
            
            String filePath = uploadDir + File.separator + uniqueFileName;
            Path targetPath = Path.of(filePath);
            
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            
            String imageUrl = "http://sircapcev.com:8080/images/view/" + uniqueFileName;
            //String imageUrl = "http://localhost:8080/images/view/" + uniqueFileName;
            return imageUrl;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String generateUniqueFileName(String originalFileName) {
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String uuid = UUID.randomUUID().toString();
        String fileExtension = extractFileExtension(originalFileName);
        
        return timeStamp + "_" + uuid + fileExtension;
    }

    private String extractFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0) {
            return fileName.substring(dotIndex);
        }
        return "";
    }

    public byte[] loadImageBytes(String filename) {
        try {
            Path file = Path.of(uploadDir + File.separator + filename);
            return Files.readAllBytes(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudo cargar el archivo");
        }
    }

    public boolean deleteImage(String fileName) throws IOException {
        String filePath = uploadDir + File.separator + fileName;
        Path targetPath = Path.of(filePath);

        return Files.deleteIfExists(targetPath);
    }

    public String replaceImage(String fileName, MultipartFile file) throws IOException {
        deleteImage(fileName);
        return storeImage(file);
    }
}