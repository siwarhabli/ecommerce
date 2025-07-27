package com.codewebprojects.ecom.services.jwt.admin.category;

import com.codewebprojects.ecom.dto.CategoryDto;
import com.codewebprojects.ecom.entity.Category;
import com.codewebprojects.ecom.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository

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
}
