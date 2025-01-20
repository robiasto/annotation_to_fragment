package de.robiasto.app.infrastructure.utility.entity_helper;

import org.springframework.web.multipart.MultipartFile;

public interface FileUtilityInterface {
    void saveFile(byte[] fileBytes, String localPath);
    void saveFile(MultipartFile file, String localPath);
    void deleteFolder(String localPath);
}
