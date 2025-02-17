package ru.tsarev.HomeAccounting.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.tsarev.HomeAccounting.dto.UserDTO;
import ru.tsarev.HomeAccounting.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
		return authService.register(userDTO);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDTO loginUserDTO) {
		return authService.login(loginUserDTO);
	}
}
