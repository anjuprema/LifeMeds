package com.lifemeds.admin.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

    @Value("${upload.folder}")
    private String uploadFolder;
	
	private String uploadDir = "";
	
    public void uploadFile(MultipartFile file, Integer idMed) {

        try {
        	uploadDir = System.getProperty("user.home");
        	//System.out.println("upload b4 dir:"+uploadDir);
        	uploadDir = uploadDir + File.separator + uploadFolder;            
        	if(!Files.isDirectory(Paths.get(uploadDir))) {
            	Files.createDirectory(Paths.get(uploadDir));
            }
        	uploadDir = uploadDir + File.separator + idMed;
        	System.out.println("upload dir aftr:"+uploadDir);
        	if(!Files.isDirectory(Paths.get(uploadDir))) {
            	Files.createDirectory(Paths.get(uploadDir));
            }else {
            	StringUtils.cleanPath(uploadDir);
            } 
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            
            System.out.println(copyLocation);
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
//            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
//                + ". Please try again!");
        }
    }
}