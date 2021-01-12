package az.ibrahim.shirinov.interviewtask.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "files")
public class DatabaseFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "path")
    private String path;

    private LocalDateTime addDate;
    private LocalDateTime updateDate;


    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @PrePersist
    public void onCreate(){
        this.addDate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        this.updateDate=LocalDateTime.now();
    }


}
