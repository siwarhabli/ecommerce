package com.codewebprojects.ecom.services.admin.category;

import com.codewebprojects.ecom.dto.CategoryDto;
import com.codewebprojects.ecom.entity.Category;
import com.codewebprojects.ecom.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CategoryServiceImpl implements CategoryService{
    private  final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public Category createcategory(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return categoryRepository.save(category);
    }
    public List<Category> getAllCategories(){
        return  categoryRepository.findAll();
    }}
