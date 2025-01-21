package ru.tsarev.HomeAccounting.models;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "money_account")
public class MoneyAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	@NotBlank(message = "Название счёта не должно быть пустым!")
	@Size(min = 2, max = 30, message = "Количество символов в названии счёта должно быть от 2 до 30!")
	private String name;

	@Column(name = "amount")
	@NotNull(message = "Поле с количеством денег на счёте не должно быть пустым!")
	@DecimalMin(value = "-100000", message = "Количество денег на счёте должно быть от -100 000 рублей до 10 000 000 рублей!")
	@DecimalMax(value = "10000000", message = "Количество денег на счёте должно быть от -100 000 рублей до 10 000 000 рублей!")
	private BigDecimal amount;

	@OneToMany(mappedBy = "account")
	private List<ExpenseRecord> records;

	public MoneyAccount() {
	}

	public MoneyAccount(String name, BigDecimal amount) {
		this.name = name;
		this.amount = amount;
	}

	public MoneyAccount(String name, BigDecimal amount, List<ExpenseRecord> records) {
		this.name = name;
		this.amount = amount;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public List<ExpenseRecord> getRecords() {
		return records;
	}

	public void setRecords(List<ExpenseRecord> records) {
		this.records = records;
	}

}
