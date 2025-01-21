package com.shubhamssd.niMapProject.service;

import com.shubhamssd.niMapProject.entities.Category;
import com.shubhamssd.niMapProject.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }




    public boolean deleteCategoryById(Integer id) {
        try {
            categoryRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public boolean updateCategory(Integer id, Category updatedCategory) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            category.setCategoryName(updatedCategory.getCategoryName());
            categoryRepository.save(category);
            return true;
        }
        return false;
    }


    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
