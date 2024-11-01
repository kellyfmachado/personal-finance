package java_project.personal_finance.repository;

import java_project.personal_finance.model.CategoryModel;
import java_project.personal_finance.model.TransactionModel;
import java_project.personal_finance.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {
    Page<TransactionModel> findByUserModel(UserModel userModel, Pageable pageable);
    Page<TransactionModel> findByCategoryModel(Optional<CategoryModel> categoryModel, Pageable pageable);
}
