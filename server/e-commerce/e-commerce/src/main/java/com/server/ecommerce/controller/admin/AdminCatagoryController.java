package com.server.ecommerce.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.ecommerce.dto.CategoryDto;
import com.server.ecommerce.entity.Category;
import com.server.ecommerce.services.admincatagory.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminCatagoryController {
	
    @Autowired
	private CategoryService categoryService;
    
    @PostMapping("category")
    public ResponseEntity<Category>createCatagory(@RequestBody CategoryDto categoryDto){
    	Category catagory =categoryService.createCategory(categoryDto);
    	return ResponseEntity.status(HttpStatus.CREATED).body(catagory);
    	
    }
    
    @GetMapping("categories")
    public ResponseEntity<List<Category>> getAllCatagories(){
    	log.info("in mappin");
    	return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
