package com.example.demo.controller;

import com.example.demo.dto.FileResponse;
import com.example.demo.entity.FileDb;
import com.example.demo.service.FileDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("file")
public class FileUploadController {
    @Autowired
    private FileDbService fileDbService;

    @PostMapping
    public FileResponse uploadFile(@RequestParam("file") MultipartFile  file) throws IOException {
        return fileDbService.store(file);
    }
    @GetMapping("/{id}")
    public FileDb getFile(@PathVariable String id) {
        return fileDbService.getFileById(id);
    }
    @GetMapping("/list")
    public List<FileResponse> getFileList(){
        return fileDbService.getFileList();
    }
}