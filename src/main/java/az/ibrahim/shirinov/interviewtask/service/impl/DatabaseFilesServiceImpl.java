package az.ibrahim.shirinov.interviewtask.service.impl;

import az.ibrahim.shirinov.interviewtask.entity.DatabaseFiles;
import az.ibrahim.shirinov.interviewtask.repository.DatabaseFilesRepository;
import az.ibrahim.shirinov.interviewtask.repository.ProductRepository;
import az.ibrahim.shirinov.interviewtask.service.DatabaseFilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class DatabaseFilesServiceImpl implements DatabaseFilesService {


    private final DatabaseFilesRepository databaseFilesRepository;
    private final ProductRepository productRepository;

    @Value("${root.upload.folder}")
    private String root;

    @Override
    public void storeFile(Long id, MultipartFile imageFile) throws IOException {


        String fileName = imageFile.getOriginalFilename();
        String type = imageFile.getContentType();

        String directory = root + id + "-IMG/";

        Path path = Paths.get(directory);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("directory created");
        }

        DatabaseFiles databaseFiles = new DatabaseFiles();
        databaseFiles.setName(fileName);
        databaseFiles.setType(type);
        databaseFiles.setPath(directory.replace("\\", "/"));
        databaseFiles.setProduct(productRepository.getOne(id));

        databaseFilesRepository.save(databaseFiles);

        imageFile.transferTo(new File(directory + imageFile.getOriginalFilename()));

    }
}



