package java_project.personal_finance.controller;

import java_project.personal_finance.dto.UpdateCategoryDto;
import java_project.personal_finance.model.CategoryModel;
import java_project.personal_finance.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<Object> addCategory (@RequestBody CategoryModel categoryModel){
        categoryService.addCategory(categoryModel);
        return ResponseEntity.ok().body("Category added");
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateCategory (@RequestBody UpdateCategoryDto updateCategoryDto){
        categoryService.updateCategory(updateCategoryDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().body("Category deleted");
    }

    @GetMapping("/list")
    public ResponseEntity<List<CategoryModel>> listAll(){
        return ResponseEntity.ok(categoryService.listAll());
    }

}
