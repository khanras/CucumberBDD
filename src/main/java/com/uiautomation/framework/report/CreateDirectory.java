package com.uiautomation.framework.report;

import com.uiautomation.framework.logger.CustomException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateDirectory {
    public static final String projectRootDirectory = System.getProperty("user.dir");
    public static String getReportPath(String folderName){
        String directory = projectRootDirectory + folderName;
        Path path = Paths.get(directory);
        if (!path.toFile().exists()){
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                new CustomException("Folder can't be created! Folder path: "+directory);
            }
        }
        return directory;
    }
}
