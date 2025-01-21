package ru.tsarev.HomeAccounting.exceptions;

public class MoneyAccountNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MoneyAccountNotFoundException() {
		super("Аккаунт не найден!");
	}

}
