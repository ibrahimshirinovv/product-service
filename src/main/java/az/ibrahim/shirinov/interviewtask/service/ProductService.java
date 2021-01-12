package az.ibrahim.shirinov.interviewtask.service;

import az.ibrahim.shirinov.interviewtask.entity.Product;
import az.ibrahim.shirinov.interviewtask.wrapper.PageableProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {


    Product save(Product product, MultipartFile  file) throws IOException;
    Product update(Long id,Product product);
    Product getById(Long id);
    PageableProduct getAll(Pageable pageable);
    void delete(Long id);
}
