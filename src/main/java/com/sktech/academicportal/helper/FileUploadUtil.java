package com.sktech.academicportal.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.Random;


public class FileUploadUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadUtil.class);

    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName); // -> create file path from our create dir and the file name selected
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save file: " + fileName, e);
        }
    }

    //-----------------------------------------New function to handle same name files -----------------------------------------------------
    public static String saveFile(String uploadDir, String fileName, MultipartFile multipartFile, Boolean replaceInPlace) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        if(!replaceInPlace){
            Random r = new Random();
            while (Files.exists(uploadPath.resolve(fileName))){

                fileName = getJustFileName(fileName).get() +  r.nextInt() + "." + getFileExtension(fileName).get();
            }
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName); // -> create file path from our create dir and the file name selected
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save file: " + fileName, e);
        }
        return fileName;
    }

    public static Optional<String> getFileExtension(String filename){

        return Optional.ofNullable(filename).filter(f -> f.contains(".")).map(f -> f.substring(filename.lastIndexOf(".")+1));
    }
    public static Optional<String> getJustFileName(String filename){

        return Optional.ofNullable(filename).filter(f -> f.contains(".")).map(f -> f.substring(0, filename.lastIndexOf(".")));
    }

    public static void cleanDirectory(String dir) {
        Path dirPath = Paths.get(dir);
        try {
            Files.list(dirPath).forEach(file -> {
                if (!Files.isDirectory((file))) {
                    try {
                        Files.delete(file);
                    } catch (IOException e) {
                        LOGGER.error("Could not delete file: " + file);
                    }
                }
            });
        } catch (IOException e) {
            LOGGER.error("Could not list directory: " + dirPath);
        }
    }

    public static void removeDir(String dir) {
        cleanDirectory(dir);
        try {
            Files.delete(Paths.get(dir));
        }catch (IOException e) {
            LOGGER.error("Could not remove directory: " + dir);
        }
    }

    public static void deleteFile(String path) {
        Path ePath = Paths.get(path);
        try {
            Files.delete(ePath);
        }catch (IOException e){
            LOGGER.error("Could not delete file: " + path);
        }
    }
}
