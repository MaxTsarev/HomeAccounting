package ru.tsarev.HomeAccounting.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MoneyAccountDTO {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private int id;

	@NotBlank(message = "Название счёта не должно быть пустым!")
	@Size(min = 2, max = 30, message = "Количество символов в названии счёта должно быть от 2 до 30!")
	private String name;

	@NotNull(message = "Поле с количеством денег на счёте не должно быть пустым!")
	@DecimalMin(value = "-100000", message = "Количество денег на счёте должно быть от -100 000 рублей до 10 000 000 рублей!")
	@DecimalMax(value = "10000000", message = "Количество денег на счёте должно быть от -100 000 рублей до 10 000 000 рублей!")
	private BigDecimal amount;

	public MoneyAccountDTO() {
	}

	public MoneyAccountDTO(String name, BigDecimal amount) {
		this.name = name;
		this.amount = amount;
	}

	public MoneyAccountDTO(int id, String name, BigDecimal amount) {
		this.id = id;
		this.name = name;
		this.amount = amount;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
