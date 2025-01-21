package ru.tsarev.HomeAccounting.exceptions;

public class CategoryNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CategoryNotFoundException() {
		super("Категория не найдена!");
	}
}
