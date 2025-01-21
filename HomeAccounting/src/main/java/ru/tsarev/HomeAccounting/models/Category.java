package ru.tsarev.HomeAccounting.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	@NotBlank(message = "Название счёта не должно быть пустым!")
	@Size(min = 2, max = 30, message = "Количество символов в названии счёта должно быть от 2 до 30!")
	private String name;

	@OneToMany(mappedBy = "category")
	private List<ExpenseRecord> records;

	public Category() {
	}

	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Category(int id, String name, List<ExpenseRecord> records) {
		this.id = id;
		this.name = name;
		this.records = records;
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

	public List<ExpenseRecord> getRecords() {
		return records;
	}

	public void setRecords(List<ExpenseRecord> records) {
		this.records = records;
	}

}
