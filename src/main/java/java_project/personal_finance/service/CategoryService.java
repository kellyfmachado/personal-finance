package java_project.personal_finance.service;

import java_project.personal_finance.dto.CategoryDto;
import java_project.personal_finance.model.CategoryModel;
import java_project.personal_finance.repository.CategoryRepository;
import java_project.personal_finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void addCategory(CategoryModel categoryModel){
/*        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        UserModel user = (UserModel) userRepository.findByEmail(userEmail);
        categoryModel.setUserModel(user);*/
        categoryRepository.save(categoryModel);
    }

    public void updateCategory(CategoryDto categoryDto) {

        CategoryModel existingCategory = categoryRepository.findById(categoryDto.getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        existingCategory.setName(categoryDto.getName());

        categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

    public List<CategoryModel> listAll(){
/*        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel userModel = (UserModel) authentication.getPrincipal();
        return categoryRepository.findByUserModel(userModel);*/
        return categoryRepository.findAll();
    }

}
