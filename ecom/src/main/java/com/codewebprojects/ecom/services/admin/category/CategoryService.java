package com.codewebprojects.ecom.services.admin.category;

import com.codewebprojects.ecom.dto.CategoryDto;
import com.codewebprojects.ecom.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createcategory(CategoryDto categoryDto);
    public List<Category> getAllCategories();
}
