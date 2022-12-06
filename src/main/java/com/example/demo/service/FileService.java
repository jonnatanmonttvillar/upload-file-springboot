package com.example.demo.service;

import com.example.demo.dto.FileResponse;
import com.example.demo.entity.FileEntity;
import com.example.demo.repository.FileRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileService {
    @Autowired
    private FileRepositoryJPA fileRepositoryJPA;
    public FileResponse store(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        FileEntity fileEntity = new FileEntity(UUID.randomUUID().toString(), fileName, file.getContentType(), file.getBytes());
        fileRepositoryJPA.save(fileEntity);
        return  mapToFileResponse(fileEntity);
    }
    public FileEntity getFileById(String id) {
        Optional<FileEntity> fileOptional = fileRepositoryJPA.findById(id);
        if(fileOptional.isPresent()) {
            return fileOptional.get();
        }
        return null;
    }
    public List<FileResponse> getFileList(){
        return fileRepositoryJPA.findAll().stream().map(this::mapToFileResponse).collect(Collectors.toList());
    }
    private FileResponse mapToFileResponse(FileEntity fileEntity) {
        return new FileResponse(fileEntity.getId(), fileEntity.getType(), fileEntity.getName());
    }


}