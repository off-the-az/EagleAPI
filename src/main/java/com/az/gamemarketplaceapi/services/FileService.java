package com.az.gamemarketplaceapi.services;

import com.az.gamemarketplaceapi.exceptions.FileStorageException;
import com.az.gamemarketplaceapi.exceptions.MyFileNotFoundException;
import com.az.gamemarketplaceapi.properties.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Objects;

@Service
public class FileService {

    private final Path fileStorageLocation;
    public static String ROOT = "/root/EagleAPI/src/main/resources/assets";
//    public static String ROOT = "C:\\Users\\0ffth\\Desktop\\GameMarketplaceAPI\\src\\main\\resources\\assets";

    @Autowired
    public FileService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(String prefix, String subprefix, MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String[] fileNameArray = fileName.split("\\.");
        try {
            System.out.println("Storing files");
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            // Copy file to the target location (Replacing existing file with the same name)
            Files.copy(file.getInputStream(), Paths.get(ROOT+"/"+prefix+"/"+subprefix+"."+fileNameArray[1]), StandardCopyOption.REPLACE_EXISTING);
            return subprefix+"."+fileNameArray[1];
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName, String dir) {
        try {
            Path filePath = this.fileStorageLocation.resolve(ROOT+"/"+dir+"/"+fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}
