package ru.tsarev.HomeAccounting.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.tsarev.HomeAccounting.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> getByUsername(String username);
}
