package az.ibrahim.shirinov.interviewtask.service.impl;

import az.ibrahim.shirinov.interviewtask.entity.Product;
import az.ibrahim.shirinov.interviewtask.repository.ProductRepository;
import az.ibrahim.shirinov.interviewtask.service.DatabaseFilesService;
import az.ibrahim.shirinov.interviewtask.service.ProductService;
import az.ibrahim.shirinov.interviewtask.wrapper.PageableProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final DatabaseFilesService databaseFilesService;


    @Override
    public Product save(Product product , MultipartFile file) throws IOException {
         product = productRepository.save(product);

         if (file != null && file.getSize() >0) {
             databaseFilesService.storeFile(product.getId(),file);
         }
         return product;
    }

    @Override
    public Product update(Long id, Product product) {
        Optional<Product> productOptional= productRepository.findById(id);
        if (productOptional.isPresent()){
            return productRepository.save(product);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,id+" id-li produkt yenilenmedi");
        }
    }

    @Override
    public PageableProduct getAll(Pageable pageable) {

        PageableProduct pageableProduct = new PageableProduct();
        Page<Product> all = productRepository.findAll(pageable);
        pageableProduct.setProducts(all.getContent());
        pageableProduct.setCurrentPage(all.getNumber());
        pageableProduct.setTotalPages(all.getTotalPages());
        pageableProduct.setTotalElements(all.getTotalElements());
        pageableProduct.setNumberOfElements(all.getNumberOfElements());

        return pageableProduct;

    }

    @Override
    public Product getById(Long id) {
        Optional<Product> temp = productRepository.findById(id);

        if (temp.isPresent()) {
            return temp.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,id+" id-li produkt tapilmadi");
        }
    }

    @Override
    public void delete(Long id) {

        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            throw new ResponseStatusException(HttpStatus.OK,id + " id-li produkt silindi");
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,id+" id-li produkt silinmedi");
        }

    }
}
