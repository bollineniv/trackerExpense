package com.example.demo.service;

import com.example.demo.model.FilesModel;
import com.example.demo.repository.FilesRepository;
import com.example.demo.util.FilesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class FilesServiceImpl implements FilesService{

    @Autowired
    UsersService usersService;

    @Autowired
    FilesUtils filesUtils;

    @Autowired
    FilesRepository filesRepository;

    @Override
    public FilesModel fileDetails(MultipartFile file) throws IOException {

        System.out.println("file: "+file);
        String filename = file.getOriginalFilename();
        System.out.println("Original Image Byte Size - " + file.getBytes().length);


        FilesModel files = new FilesModel(usersService.getLoggedInUser().getId(),
                                    filename,file.getContentType(),filesUtils.compressBytes(file.getBytes()));
//        filesRepository.save(files);
//        files.setUserId(usersService.getLoggedInUser().getId());
//        files.setName(filename);
//        files.setType(file.getContentType());
//        files.setPicByte(filesUtils.compressBytes(file.getBytes()));
//        System.out.println("file: "+files );
        return filesRepository.save(files);

    }

    @Override
    public FilesModel getReceipt(String receiptName) {
        final FilesModel reciept = filesRepository.findByName(receiptName);

        FilesModel retrivedReceipt = new FilesModel(usersService.getLoggedInUser().getId(),
                receiptName,reciept.getType(),filesUtils.decompressBytes(reciept.getPicByte()));

        return retrivedReceipt;

    }


    @Override
    public void fileUpload(MultipartFile file) {

    }


}
