package az.ibrahim.shirinov.interviewtask.api;

import az.ibrahim.shirinov.interviewtask.entity.Category;
import az.ibrahim.shirinov.interviewtask.entity.Product;
import az.ibrahim.shirinov.interviewtask.service.CategoryService;
import az.ibrahim.shirinov.interviewtask.service.ProductService;
import az.ibrahim.shirinov.interviewtask.wrapper.PageableProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;


    @GetMapping("{id}")
    public Product getProductById(@PathVariable Long id){
        Product product = productService.getById(id);
        product.getDatabaseFiles().forEach(databaseFile -> {
            databaseFile.setPath(databaseFile.getPath() + databaseFile.getName());
        } );
        return product;
    }


    @GetMapping
    public PageableProduct getAll(@PageableDefault Pageable pageable){
        return productService.getAll(pageable);
    }



    @PostMapping
    public Product saveProduct(
                               @RequestParam(name = "name") String name ,
                               @RequestParam(name = "price") BigDecimal price,
                               @RequestParam(name = "available") int available,
                               @RequestParam(name = "description") String description,
                               @RequestParam(name = "categoryId") Long categoryId,
                               @RequestParam(required = false) MultipartFile  multipartFile) throws IOException {
        Category category = new Category();
        category.setId(categoryId);

            Product product = Product.builder()
                    .name(name)
                    .price(price)
                    .available(available)
                    .description(description)
                    .category(category)
                    .build();


            return productService.save(product, multipartFile);


    }

    @PutMapping("{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable Long id) {
        return productService.update(id,product);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.delete(id);

    }

}
