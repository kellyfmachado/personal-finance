package java_project.personal_finance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "categoryModel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<TransactionModel> transactionModels;

}
