package az.ibrahim.shirinov.interviewtask.repository;

import az.ibrahim.shirinov.interviewtask.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product,Long> {


}
