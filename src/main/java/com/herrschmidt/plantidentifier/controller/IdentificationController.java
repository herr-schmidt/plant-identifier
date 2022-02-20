package com.herrschmidt.plantidentifier.controller;

import java.io.IOException;

import com.herrschmidt.plantidentifier.service.IdentificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin
public class IdentificationController {

    @Autowired
    IdentificationService identificationService;

    @PostMapping(value = "/identify", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<String> classifyPlant(@RequestBody byte[] plantImage) {
        String image = null;
        try {
            image = identificationService.identify(plantImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

}
