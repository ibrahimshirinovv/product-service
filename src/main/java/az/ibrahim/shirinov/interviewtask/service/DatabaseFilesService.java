package az.ibrahim.shirinov.interviewtask.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DatabaseFilesService {

    void storeFile(Long id,MultipartFile  multipartFile) throws IOException;

}
