package az.ibrahim.shirinov.interviewtask.repository;

import az.ibrahim.shirinov.interviewtask.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    /*Category> findByName(String name);*/

    Optional<Category> findByName(String name);
}
