package ru.tsarev.HomeAccounting.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import ru.tsarev.HomeAccounting.dto.MoneyAccountDTO;
import ru.tsarev.HomeAccounting.models.MoneyAccount;

@Component
public class MoneyAccountMapper {

	private final ModelMapper modelMapper = new ModelMapper();

	public MoneyAccount convertToMoneyAccount(MoneyAccountDTO moneyAccountDTO) {
		return modelMapper.map(moneyAccountDTO, MoneyAccount.class);
	}

	public MoneyAccountDTO convertToMoneyAccountDTO(MoneyAccount moneyAccount) {
		return modelMapper.map(moneyAccount, MoneyAccountDTO.class);
	}
}
