package ru.tsarev.HomeAccounting.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryDTO {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private int id;

	@NotBlank(message = "Название категории не должно быть пустым!")
	@Size(min = 3, max = 30, message = "Количество символов в названии категории должно быть от 3 до 30!")
	private String name;

	public CategoryDTO() {
	}

	public CategoryDTO(String name) {
		this.name = name;
	}

	public CategoryDTO(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
