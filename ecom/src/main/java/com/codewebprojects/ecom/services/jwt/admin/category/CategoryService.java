package com.codewebprojects.ecom.services.jwt.admin.category;

import com.codewebprojects.ecom.dto.CategoryDto;
import com.codewebprojects.ecom.entity.Category;

public interface CategoryService {
    Category createcategory(CategoryDto categoryDto);
}
