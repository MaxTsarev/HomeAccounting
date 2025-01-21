package ru.tsarev.HomeAccounting.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ExpenseRecordDTO {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private int id;

	@NotNull(message = "Сумма операции не должна быть пустой!")
	@DecimalMin(value = "1", message = "Минимальная сумма операции 1 ед.!")
	@DecimalMax(value = "5000000", message = "Максимальная сумма операции 5 000 000 ед.!")
	private BigDecimal sum;

	@NotBlank(message = "Название счёта не должно быть пустым!")
	@Size(min = 2, max = 30, message = "Количество символов в названии счёта должно быть от 2 до 30!")
	private String nameAccount;

	@NotBlank(message = "Название категории не должно быть пустым!")
	@Size(min = 3, max = 30, message = "Количество символов в названии категории должно быть от 3 до 30!")
	private String nameCategory;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime dateOperation;

	public ExpenseRecordDTO() {
	}

	public ExpenseRecordDTO(BigDecimal sum, String nameAccount, String nameCategory) {
		this.sum = sum;
		this.nameAccount = nameAccount;
		this.nameCategory = nameCategory;
	}

	public ExpenseRecordDTO(int id, BigDecimal sum, String nameAccount, String nameCategory) {
		this.id = id;
		this.sum = sum;
		this.nameAccount = nameAccount;
		this.nameCategory = nameCategory;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public String getNameAccount() {
		return nameAccount;
	}

	public void setNameAccount(String nameAccount) {
		this.nameAccount = nameAccount;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public LocalDateTime getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(LocalDateTime dateOperation) {
		this.dateOperation = dateOperation;
	}

}
