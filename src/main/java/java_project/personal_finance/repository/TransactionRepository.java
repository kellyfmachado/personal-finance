package java_project.personal_finance.repository;

import java_project.personal_finance.model.TransactionModel;
import java_project.personal_finance.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {
    List<TransactionModel> findByUserModel(UserModel userModel);
}
