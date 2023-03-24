package com.example.demo.service;

import com.example.demo.model.FilesModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FilesService {

    FilesModel fileDetails(MultipartFile file) throws IOException;

    FilesModel getReceipt(String imageName);

    void fileUpload(MultipartFile file);
}
