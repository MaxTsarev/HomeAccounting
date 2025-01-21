package ru.tsarev.HomeAccounting.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "expense_record")
public class ExpenseRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "date_operation")
	private LocalDateTime dateOperation;

	@Column(name = "sum")
	@NotNull(message = "Сумма операции не должна быть пустой!")
	@DecimalMin(value = "1", message = "Минимальная сумма операции 1 ед.!")
	@DecimalMax(value = "5000000", message = "Максимальная сумма операции 5 000 000 ед.!")
	private BigDecimal sum;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "money_account_id", referencedColumnName = "id")
	private MoneyAccount account;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;

	public ExpenseRecord() {
	}

	public ExpenseRecord(LocalDateTime dateOperation, BigDecimal sum) {
		this.dateOperation = dateOperation;
		this.sum = sum;
	}

	public ExpenseRecord(LocalDateTime dateOperation, BigDecimal sum, MoneyAccount account, Category category) {
		this.dateOperation = dateOperation;
		this.sum = sum;
		this.account = account;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(LocalDateTime dateOperation) {
		this.dateOperation = dateOperation;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public MoneyAccount getAccount() {
		return account;
	}

	public void setAccount(MoneyAccount account) {
		this.account = account;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
