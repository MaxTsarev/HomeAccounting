package ru.tsarev.HomeAccounting.services;

import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.tsarev.HomeAccounting.models.User;
import ru.tsarev.HomeAccounting.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + username));

		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().getName());

		return org.springframework.security.core.userdetails.User.builder().username(user.getUsername())
				.password(user.getPassword()).authorities(Collections.singletonList(authority)).build();
	}

}
