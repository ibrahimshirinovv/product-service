package az.ibrahim.shirinov.interviewtask.wrapper;

import az.ibrahim.shirinov.interviewtask.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableProduct {

    private List<Product> products;
    private int totalPages;
    private Long totalElements;
    private int numberOfElements;
    private int currentPage;



}
