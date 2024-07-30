package java_project.personal_finance.repository;

import java_project.personal_finance.model.CategoryModel;
import java_project.personal_finance.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
    List<CategoryModel> findByUserModel(UserModel userModel);
}
