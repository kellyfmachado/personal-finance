package java_project.personal_finance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@Entity
@Getter
@Setter
public class TransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private Double amount;
    private String type;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModel categoryModel;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;

}

