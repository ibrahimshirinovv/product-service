package az.ibrahim.shirinov.interviewtask.service.impl;

import az.ibrahim.shirinov.interviewtask.entity.Category;
import az.ibrahim.shirinov.interviewtask.repository.CategoryRepository;
import az.ibrahim.shirinov.interviewtask.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getById(Long id) {

        Optional<Category> categoryDb = categoryRepository.findById(id);

        if (categoryDb.isPresent()) {
           return categoryDb.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,id+" id-li kategoriya tapilmadi");
    }

    @Override
    public List<Category> getAll() {
        return  categoryRepository.findAll();
    }

    @Override
    public Category update(Long id, Category editCategory) {

        Optional<Category> opt = categoryRepository.findById(id);

        if (opt.isPresent()){
            return categoryRepository.save(editCategory);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,id+" id-li produkt tapilmadi");
        }
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }



    @Override
    public Category findByName(String name) {
        Optional<Category> opt = categoryRepository.findByName(name);

        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,name +" adli produkt tapilmadi");
        }
    }
}
