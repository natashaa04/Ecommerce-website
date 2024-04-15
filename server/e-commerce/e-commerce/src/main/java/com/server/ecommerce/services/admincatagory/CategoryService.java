package com.server.ecommerce.services.admincatagory;

import java.util.List;

import com.server.ecommerce.dto.CategoryDto;
import com.server.ecommerce.entity.Category;

public interface CategoryService {
	
	public Category createCategory(CategoryDto categoryDto);
	
	   public List<Category> getAllCategories();
}
