package com.example.demo.controller;

import com.example.demo.model.FilesModel;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.FilesService;
import com.example.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;

@RestController
@CrossOrigin("*")
public class FilesController {

    @Autowired
    UsersService usersService;

    @Autowired
    FilesService filesService;


    @PostMapping("/upload")
    public ResponseEntity<String> fileUpload( @RequestParam("file") MultipartFile file){
        String message;
        System.out.println("inside upload");
//        file.setUserId(usersService.getLoggedInUser().getId());
        try {
            filesService.fileDetails(file);
            message = "File Uploaded Successfully " + file.getOriginalFilename();
            return new ResponseEntity<String>(message, HttpStatus.OK);
        }
        catch (Exception e){
            message = "File upload unsuccessful "+file.getOriginalFilename();
            return new ResponseEntity<>(message,HttpStatus.EXPECTATION_FAILED);
        }
    }

}
