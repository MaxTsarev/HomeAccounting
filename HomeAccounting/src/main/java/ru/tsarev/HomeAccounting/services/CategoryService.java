package ru.tsarev.HomeAccounting.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.tsarev.HomeAccounting.dto.CategoryDTO;
import ru.tsarev.HomeAccounting.exceptions.CategoryNotFoundException;
import ru.tsarev.HomeAccounting.models.Category;
import ru.tsarev.HomeAccounting.repositories.CategoryRepository;

@Service
@Transactional(readOnly = true)
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final ModelMapper modelMapper;

	public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}

	@Transactional
	public void save(CategoryDTO categoryDTO) {
		categoryRepository.save(convertToCategory(categoryDTO));
	}

	@Transactional
	public List<CategoryDTO> findAll() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDTO> categoriesDTO = new ArrayList<>();
		for (Category cat : categories) {
			categoriesDTO.add(convertToCategoryDTO(cat));
		}
		return categoriesDTO;
	}

	@Transactional
	public Optional<Category> getCategoryById(int id) {
		return categoryRepository.getCategoryById(id);
	}

	@Transactional
	public Optional<Category> getByName(String name) {
		return categoryRepository.getByName(name);
	}

	@Transactional
	public void updateCategory(int id, CategoryDTO categoryDTO) {
		if (getCategoryById(id).isEmpty()) {
			throw new CategoryNotFoundException();
		}
		Category category = getCategoryById(id).get();
		category.setName(categoryDTO.getName());
	}

	@Transactional
	public void deleteCategory(int id) {
		if (getCategoryById(id).isEmpty()) {
			throw new CategoryNotFoundException();
		}
		Category category = getCategoryById(id).get();
		categoryRepository.delete(category);
	}

	public Category convertToCategory(CategoryDTO categoryDTO) {
		return modelMapper.map(categoryDTO, Category.class);
	}

	public CategoryDTO convertToCategoryDTO(Category category) {
		return modelMapper.map(category, CategoryDTO.class);
	}
}
