package ru.tsarev.HomeAccounting.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ru.tsarev.HomeAccounting.dto.CategoryDTO;
import ru.tsarev.HomeAccounting.exceptions.CategoryNotFoundException;
import ru.tsarev.HomeAccounting.exceptions.MyErrorResponse;
import ru.tsarev.HomeAccounting.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping()
	public ResponseEntity<HttpStatus> createCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
		categoryService.save(categoryDTO);
		return ResponseEntity.ok(HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<CategoryDTO>> readCategory() {
		return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<HttpStatus> updateCategory(@PathVariable int id,
			@RequestBody @Valid CategoryDTO categoryDTO) {
		categoryService.updateCategory(id, categoryDTO);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteCategory(@PathVariable int id) {
		categoryService.deleteCategory(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@ExceptionHandler
	public ResponseEntity<MyErrorResponse> handleException(CategoryNotFoundException e) {
		MyErrorResponse er = new MyErrorResponse(e.getMessage());
		return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
	}

}
