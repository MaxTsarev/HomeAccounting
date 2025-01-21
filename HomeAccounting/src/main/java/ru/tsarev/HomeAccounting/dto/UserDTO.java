package ru.tsarev.HomeAccounting.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {

	@NotBlank(message = "Имя пользователя не должно быть пустым!")
	@Size(min = 2, max = 30, message = "Количество символов в имени пользователя должно быть от 2 до 30!")
	private String username;

	@NotBlank(message = "Пароль не должен быть пустым!")
	@Size(min = 2, max = 30, message = "Количество символов пароле должно быть от 2 до 30!")
	private String password;

	public UserDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
