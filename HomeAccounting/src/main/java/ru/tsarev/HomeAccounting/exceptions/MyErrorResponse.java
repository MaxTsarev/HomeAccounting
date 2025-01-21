package ru.tsarev.HomeAccounting.exceptions;

public class MyErrorResponse {

	private String message;

	public MyErrorResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
