package com.cev.sircapcev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.cev.sircapcev.services.ImagesStorageService;

import java.io.IOException;

@RestController
@RequestMapping("/images")
@CrossOrigin
public class MediaFilesController {

    
    @Autowired
    ImagesStorageService imageStorageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = imageStorageService.storeImage(file);
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<Void> deleteImage(@PathVariable String fileName) {
        try {
            boolean deleted = imageStorageService.deleteImage(fileName);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<byte[]> viewImage(@PathVariable String fileName) {
        try {
            byte[] imageBytes = imageStorageService.loadImageBytes(fileName);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageBytes);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/replace/{fileName}")
    public ResponseEntity<String> replaceImage(
            @PathVariable String fileName,
            @RequestParam("file") MultipartFile file) {
        try {
            String newImageUrl = imageStorageService.replaceImage(fileName, file);
            return ResponseEntity.ok(newImageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
