package java_project.personal_finance.service;

import java_project.personal_finance.dto.UpdateCategoryDto;
import java_project.personal_finance.model.CategoryModel;
import java_project.personal_finance.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void addCategory(CategoryModel categoryModel){
        categoryRepository.save(categoryModel);
    }

    public void updateCategory(UpdateCategoryDto updateCategoryDto) {

        CategoryModel existingCategory = categoryRepository.findById(updateCategoryDto.getId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + updateCategoryDto.getId()));

        existingCategory.setName(updateCategoryDto.getName());

        categoryRepository.save(existingCategory);
    }

    public void deleteCategory(UUID id){
        categoryRepository.deleteById(id);
    }

    public List<CategoryModel> listAll(){
        return categoryRepository.findAll();
    }

}
