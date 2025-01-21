package ru.tsarev.HomeAccounting.exceptions;

public class ExpenseRecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExpenseRecordNotFoundException() {
		super("Запись расходов не найдена!");
	}

}
