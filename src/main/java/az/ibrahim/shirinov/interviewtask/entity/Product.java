package az.ibrahim.shirinov.interviewtask.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Zehmet olmazsa mehsulun adini daxil edin")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Zehmet olmazsa mehsulun qiymetini daxil edin")
    @Min(value = 1 ,message = "Mehsulun qiymeti 0 ola bilmez")
    @Column(name = "price")
    private BigDecimal price;

    @NotNull(message = "Zehmet olmazsa mehsulun sayini qeyd edin")
    @Min(value = 1 , message = "Mehsul sayi 0-dan cox olmalidir")
    @Column(name = "available")
    private int available;

    @Column(name = "add_date")
    private LocalDateTime addDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;
    @NotBlank( message = "Zehmet olmazsa mehsulun xususiyyetleri barede melumatlari qeyd edin)")
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<DatabaseFiles> databaseFiles;

    @PrePersist
    private void onCreate(){
        this.addDate = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate(){
        this.updateDate=LocalDateTime.now();
    }
}