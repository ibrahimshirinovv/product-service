package az.ibrahim.shirinov.interviewtask.service;

import az.ibrahim.shirinov.interviewtask.entity.Category;

import java.util.List;

public interface CategoryService {

    Category save(Category category);
    Category getById(Long id);
    List<Category> getAll();
    Category update(Long id,Category category);
    void delete(Long id);
    Category findByName(String name);
}
