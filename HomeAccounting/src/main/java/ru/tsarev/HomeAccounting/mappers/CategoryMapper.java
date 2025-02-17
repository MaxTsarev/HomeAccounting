package ru.tsarev.HomeAccounting.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import ru.tsarev.HomeAccounting.dto.CategoryDTO;
import ru.tsarev.HomeAccounting.models.Category;

@Component
public class CategoryMapper {

	private final ModelMapper modelMapper = new ModelMapper();

	public Category convertToCategory(CategoryDTO categoryDTO) {
		return modelMapper.map(categoryDTO, Category.class);
	}

	public CategoryDTO convertToCategoryDTO(Category category) {
		return modelMapper.map(category, CategoryDTO.class);
	}
}
