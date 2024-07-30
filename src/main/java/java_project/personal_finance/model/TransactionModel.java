package java_project.personal_finance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @ManyToOne
    private CategoryModel categoryModel;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    public CategoryModel getCategory() {
        return categoryModel;
    }

    public void setCategory(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }
}

