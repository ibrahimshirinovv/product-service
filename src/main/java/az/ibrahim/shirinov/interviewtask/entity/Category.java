package az.ibrahim.shirinov.interviewtask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "kategoriya adi bos ola bilmez")
    @Column(name = "name")
    private String name;
    @Column(name = "add_date")
    private LocalDateTime addDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;


    @PrePersist
    public void onCreate(){
        this.addDate=LocalDateTime.now();
    }
    @PreUpdate
    public void onUpdate(){
        this.updateDate=LocalDateTime.now();
    }

}