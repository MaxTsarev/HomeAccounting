package ru.tsarev.HomeAccounting.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ru.tsarev.HomeAccounting.models.User;
import ru.tsarev.HomeAccounting.repositories.RoleRepository;
import ru.tsarev.HomeAccounting.repositories.UserRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

	@Value("${admin_password}")
	private String adminPassword;

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public AdminInitializer(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {
		// Создаем первого администратора, если его еще нет
		if (userRepository.getByUsername("admin").isEmpty()) {
			User admin = new User();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode(adminPassword));
			admin.setRole(roleRepository.getByName("ADMIN"));
			userRepository.save(admin);
			System.out.println("Пользователь admin создан: username=admin, password=" + adminPassword);
		}
	}
}
