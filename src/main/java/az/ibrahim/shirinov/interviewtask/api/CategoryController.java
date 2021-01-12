package az.ibrahim.shirinov.interviewtask.api;

import az.ibrahim.shirinov.interviewtask.entity.Category;
import az.ibrahim.shirinov.interviewtask.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

        private final CategoryService categoryService;


    @GetMapping("{id}")
    public Category getCategory(@PathVariable Long id){
        return categoryService.getById(id);
    }
    @PostMapping
    public Category saveCategory(@RequestBody Category category){
        return categoryService.save(category);
    }

    @PutMapping("{id}")
    public Category updateCategory(@RequestBody Category category, @PathVariable Long id) {
        return categoryService.update(id,category);
    }

    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @GetMapping("/getAll")
    public List<Category> getAllCategories(){
        return categoryService.getAll();
    }

}
