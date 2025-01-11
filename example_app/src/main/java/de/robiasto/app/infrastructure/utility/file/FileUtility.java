package de.robiasto.app.infrastructure.utility.file;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

@Service
@NoArgsConstructor
public class FileUtility {

    @Value("${file.storage.path:example_app/build/resources/main/static}")
    private String folderRootPath;

    public void saveFile(byte[] fileBytes, String localPath) {
        try {
            Path filePath = Paths.get(folderRootPath, localPath).toAbsolutePath().normalize();

            File directory = filePath.getParent().toFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }

            try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
                fos.write(fileBytes);
            }
        } catch (IOException e) {
            throw new FileUtilityException("Error while saving file");
        }
    }

    public void saveFile(MultipartFile file, String localPath){
        if(file.isEmpty()){
            return;
        }

        try {
            this.saveFile(file.getBytes(), localPath);
        } catch (IOException e) {
            throw new FileUtilityException("Error while saving MultipartFile");
        }
    }

    public void deleteFolder(String localPath) {
        Path directory = Paths.get(folderRootPath, localPath).toAbsolutePath().normalize();

        if (!Files.exists(directory)) {
            return;
        }

        try {
            Files.walkFileTree(directory, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new FileUtilityException("Error while deleting folder and its contents");
        }
    }

}
