package com.pet.buyselltrade.controllers;

import com.pet.buyselltrade.models.ImageModel;
import com.pet.buyselltrade.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class ImagesController {
    private final ImageRepository imageRepository;

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id){
        ImageModel imageModel =  imageRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName",imageModel.getFileName())
                .contentType(MediaType.valueOf(imageModel.getType()))
                .contentLength(imageModel.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(imageModel.getBytes())));

    }

}
