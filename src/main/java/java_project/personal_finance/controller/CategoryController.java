package java_project.personal_finance.controller;

import java_project.personal_finance.dto.CategoryDto;
import java_project.personal_finance.model.CategoryModel;
import java_project.personal_finance.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateCategory (@RequestBody CategoryDto categoryDto){
        categoryService.updateCategory(categoryDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<Page<CategoryModel>> list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "18") int size){
        Page<CategoryModel> categories = categoryService.list(page, size);
        return ResponseEntity.ok(categories);
    }

}
