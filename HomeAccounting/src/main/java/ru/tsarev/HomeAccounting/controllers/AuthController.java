package ru.tsarev.HomeAccounting.controllers;

import java.util.Collections;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.tsarev.HomeAccounting.dto.UserDTO;
import ru.tsarev.HomeAccounting.models.Role;
import ru.tsarev.HomeAccounting.models.User;
import ru.tsarev.HomeAccounting.repositories.RoleRepository;
import ru.tsarev.HomeAccounting.repositories.UserRepository;
import ru.tsarev.HomeAccounting.util.JwtUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;
	private final JwtUtils jwtUtils;

	public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils,
			RoleRepository roleRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
		this.jwtUtils = jwtUtils;
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
		Optional<User> existingUser = userRepository.getByUsername(userDTO.getUsername());
		if (existingUser.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Данный пользователь уже существует!");
		}
		User user = modelMapper.map(userDTO, User.class);
		Role userRole = roleRepository.getByName("USER");
		user.setRole(userRole);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь успешно зарегистрирован!");
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDTO loginUserDTO) {
		Optional<User> userOptional = userRepository.getByUsername(loginUserDTO.getUsername());

		if (userOptional.isEmpty()
				|| !passwordEncoder.matches(loginUserDTO.getPassword(), userOptional.get().getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверные логин или пароль!");
		}

		String token = jwtUtils.generateToken(userOptional.get().getUsername());
		return ResponseEntity.ok(Collections.singletonMap("token", "Bearer " + token));
	}
}
