package ru.tsarev.HomeAccounting.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.tsarev.HomeAccounting.dto.MoneyAccountDTO;
import ru.tsarev.HomeAccounting.exceptions.MoneyAccountNotFoundException;
import ru.tsarev.HomeAccounting.mappers.MoneyAccountMapper;
import ru.tsarev.HomeAccounting.models.MoneyAccount;
import ru.tsarev.HomeAccounting.repositories.MoneyAccountRepository;

@Service
@Transactional(readOnly = true)
public class MoneyAccountService {

	private final MoneyAccountRepository accountRepository;
	private final MoneyAccountMapper moneyAccountMapper;

	public MoneyAccountService(MoneyAccountRepository accountRepository, MoneyAccountMapper moneyAccountMapper) {
		this.accountRepository = accountRepository;
		this.moneyAccountMapper = moneyAccountMapper;
	}

	@Transactional
	public void save(MoneyAccountDTO accountDTO) {
		accountRepository.save(moneyAccountMapper.convertToMoneyAccount(accountDTO));
	}

	@Transactional
	public List<MoneyAccountDTO> findAll() {
		List<MoneyAccount> moneyAccounts = accountRepository.findAll();
		List<MoneyAccountDTO> moneyAccountsDTO = new ArrayList<>();
		for (MoneyAccount account : moneyAccounts) {
			moneyAccountsDTO.add(moneyAccountMapper.convertToMoneyAccountDTO(account));
		}
		return moneyAccountsDTO;
	}

	@Transactional
	public Optional<MoneyAccount> getMoneyAccountById(int id) {
		return accountRepository.getMoneyAccountById(id);
	}

	@Transactional
	public Optional<MoneyAccount> getByName(String name) {
		return accountRepository.getByName(name);
	}

	@Transactional
	public void updateMoneyAccount(int id, MoneyAccountDTO accountDTO) {
		if (accountRepository.getMoneyAccountById(id).isEmpty()) {
			throw new MoneyAccountNotFoundException();
		}
		MoneyAccount moneyAccount = getMoneyAccountById(id).get();
		moneyAccount.setName(accountDTO.getName());
		moneyAccount.setAmount(accountDTO.getAmount());
	}

	@Transactional
	public void deleteMoneyAccount(int id) {
		if (accountRepository.getMoneyAccountById(id).isEmpty()) {
			throw new MoneyAccountNotFoundException();
		}
		accountRepository.deleteById(id);
	}
}
