package ru.tsarev.HomeAccounting.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.tsarev.HomeAccounting.models.MoneyAccount;

@Repository
public interface MoneyAccountRepository extends JpaRepository<MoneyAccount, Integer> {
	Optional<MoneyAccount> getMoneyAccountById(int id);

	Optional<MoneyAccount> getByName(String name);
}
