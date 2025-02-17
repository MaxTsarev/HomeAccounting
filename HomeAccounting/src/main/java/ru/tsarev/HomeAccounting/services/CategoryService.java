package ru.tsarev.HomeAccounting.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.tsarev.HomeAccounting.dto.CategoryDTO;
import ru.tsarev.HomeAccounting.exceptions.CategoryNotFoundException;
import ru.tsarev.HomeAccounting.mappers.CategoryMapper;
import ru.tsarev.HomeAccounting.models.Category;
import ru.tsarev.HomeAccounting.repositories.CategoryRepository;

@Service
@Transactional(readOnly = true)
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;

	public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
		this.categoryRepository = categoryRepository;
		this.categoryMapper = categoryMapper;
	}

	@Transactional
	public void save(CategoryDTO categoryDTO) {
		categoryRepository.save(categoryMapper.convertToCategory(categoryDTO));
	}

	@Transactional
	public List<CategoryDTO> findAll() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDTO> categoriesDTO = new ArrayList<>();
		for (Category cat : categories) {
			categoriesDTO.add(categoryMapper.convertToCategoryDTO(cat));
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
}
