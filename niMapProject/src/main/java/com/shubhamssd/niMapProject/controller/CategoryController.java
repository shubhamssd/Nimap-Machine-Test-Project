package com.shubhamssd.niMapProject.controller;

import com.shubhamssd.niMapProject.entities.Category;
import com.shubhamssd.niMapProject.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/categories")
    public ResponseEntity<Page<Category>> findAll(Pageable pageable) {
        return ResponseEntity.ok(categoryService.findAll(pageable));
    }

    @PostMapping("/categories")
    public ResponseEntity<String> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category Created", HttpStatus.CREATED);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryByIdId(@PathVariable Integer id){
        Category category = categoryService.getCategoryById(id);
        if(category != null)
            return new ResponseEntity<>(category, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id){
        boolean deleted = categoryService.deleteCategoryById(id);
        if(deleted)
            return new ResponseEntity<>("Category deleted successfully ",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Integer id, @RequestBody Category updatedCategory){
        boolean updated = categoryService.updateCategory(id, updatedCategory);
        if(updated)
            return new ResponseEntity<>("Category updated successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
