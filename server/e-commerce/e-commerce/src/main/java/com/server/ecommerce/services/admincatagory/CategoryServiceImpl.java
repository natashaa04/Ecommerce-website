package com.server.ecommerce.services.admincatagory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.ecommerce.dto.CategoryDto;
import com.server.ecommerce.entity.Category;
import com.server.ecommerce.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	 private  CategoryRepository categoryRepository;
	
	public Category createCategory(CategoryDto categoryDto) {
		Category category = new Category();
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		return categoryRepository.save(category);	
	}
	
	   public List<Category> getAllCategories(){
		   try {
			   log.info("in cata");
		   List<Category>cat= categoryRepository.findAll();
		   return cat;
		   }
		   catch(Exception e){
			   log.info("categories err:{}");
			   e.printStackTrace();
			   return null;
		   }
	   }



}
