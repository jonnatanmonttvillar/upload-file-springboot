package com.example.demo.controller;

import com.example.demo.dto.FileResponse;
import com.example.demo.entity.FileEntity;
import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("file")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping
    public FileResponse uploadFile(@RequestParam("file") MultipartFile  file) throws IOException {
        return fileService.store(file);
    }
    @GetMapping("/{id}")
    public FileEntity getFile(@PathVariable String id) {
        return fileService.getFileById(id);
    }
    @GetMapping("/list")
    public List<FileResponse> getFileList(){
        return fileService.getFileList();
    }
}