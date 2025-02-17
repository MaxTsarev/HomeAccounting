package ru.tsarev.HomeAccounting.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.tsarev.HomeAccounting.dto.ExpenseRecordDTO;
import ru.tsarev.HomeAccounting.exceptions.CategoryNotFoundException;
import ru.tsarev.HomeAccounting.exceptions.ExpenseRecordNotFoundException;
import ru.tsarev.HomeAccounting.exceptions.MoneyAccountNotFoundException;
import ru.tsarev.HomeAccounting.mappers.ExpenseRecordMapper;
import ru.tsarev.HomeAccounting.models.ExpenseRecord;
import ru.tsarev.HomeAccounting.models.MoneyAccount;
import ru.tsarev.HomeAccounting.repositories.ExpenseRecordRepository;

@Service
@Transactional(readOnly = true)
public class ExpenseRecordService {

	private final ExpenseRecordRepository recordRepository;
	private final CategoryService categoryService;
	private final MoneyAccountService moneyAccountService;
	private final ExpenseRecordMapper expenseRecordMapper;

	public ExpenseRecordService(ExpenseRecordRepository recordRepository, CategoryService categoryService,
			MoneyAccountService moneyAccountService, ExpenseRecordMapper expenseRecordMapper) {
		this.recordRepository = recordRepository;
		this.categoryService = categoryService;
		this.moneyAccountService = moneyAccountService;
		this.expenseRecordMapper = expenseRecordMapper;
	}

	@Transactional
	public void save(ExpenseRecordDTO expenseRecordDTO) {
		if (moneyAccountService.getByName(expenseRecordDTO.getNameAccount()).isEmpty()) {
			throw new MoneyAccountNotFoundException();
		}
		if (categoryService.getByName(expenseRecordDTO.getNameCategory()).isEmpty()) {
			throw new CategoryNotFoundException();
		}
		MoneyAccount moneyAccount = moneyAccountService.getByName(expenseRecordDTO.getNameAccount()).get();
		moneyAccount.setAmount(subtractValue(moneyAccount, expenseRecordDTO));
		ExpenseRecord expenseRecord = expenseRecordMapper.convertToExpenseRecord(expenseRecordDTO);
		recordRepository.save(enrichExpenseRecord(expenseRecord, expenseRecordDTO));
	}

	@Transactional
	public List<ExpenseRecordDTO> findAll() {
		List<ExpenseRecord> expenseRecords = recordRepository.findAll();
		List<ExpenseRecordDTO> expenseRecordDTOs = new ArrayList<>();
		for (ExpenseRecord expenseRecord : expenseRecords) {
			expenseRecordDTOs.add(expenseRecordMapper.convertToExpenseRecordDTO(expenseRecord));
		}
		return expenseRecordDTOs;
	}

	@Transactional
	public List<ExpenseRecordDTO> readRecordOnAccount(int id) {
		if (moneyAccountService.getMoneyAccountById(id).isEmpty()) {
			throw new MoneyAccountNotFoundException();
		}
		List<ExpenseRecord> listRecords = moneyAccountService.getMoneyAccountById(id).get().getRecords();
		List<ExpenseRecordDTO> listRecordDTOs = new ArrayList<>();
		for (ExpenseRecord exRecord : listRecords) {
			listRecordDTOs.add(expenseRecordMapper.convertToExpenseRecordDTO(exRecord));
		}
		return listRecordDTOs;
	}

	@Transactional
	public List<ExpenseRecordDTO> readRecordOnCategory(int id) {
		if (categoryService.getCategoryById(id).isEmpty()) {
			throw new CategoryNotFoundException();
		}
		List<ExpenseRecord> listRecords = categoryService.getCategoryById(id).get().getRecords();
		List<ExpenseRecordDTO> listRecordDTOs = new ArrayList<>();
		for (ExpenseRecord exRecord : listRecords) {
			listRecordDTOs.add(expenseRecordMapper.convertToExpenseRecordDTO(exRecord));
		}
		return listRecordDTOs;
	}

	@Transactional
	public Optional<ExpenseRecord> getExpenseRecordById(int id) {
		return recordRepository.getExpenseRecordById(id);
	}

	@Transactional
	public void updateExpenseRecord(int id, ExpenseRecordDTO expenseRecordDTO) {
		if (getExpenseRecordById(id).isEmpty()) {
			throw new ExpenseRecordNotFoundException();
		}
		if (moneyAccountService.getByName(expenseRecordDTO.getNameAccount()).isEmpty()) {
			throw new MoneyAccountNotFoundException();
		}
		if (categoryService.getByName(expenseRecordDTO.getNameCategory()).isEmpty()) {
			throw new CategoryNotFoundException();
		}
		ExpenseRecord expenseRecord = getExpenseRecordById(id).get();
		MoneyAccount moneyAccount = getExpenseRecordById(id).get().getAccount();
		moneyAccount.setAmount(moneyAccount.getAmount().add(expenseRecord.getSum()));
		moneyAccount.setAmount(subtractValue(moneyAccount, expenseRecordDTO));
		expenseRecord.setSum(expenseRecordDTO.getSum());
		recordRepository.save(enrichExpenseRecord(expenseRecord, expenseRecordDTO));
	}

	@Transactional
	public void deleteExpenseRecord(int id) {
		if (getExpenseRecordById(id).isEmpty()) {
			throw new ExpenseRecordNotFoundException();
		}
		ExpenseRecord expenseRecord = getExpenseRecordById(id).get();
		MoneyAccount moneyAccount = getExpenseRecordById(id).get().getAccount();
		moneyAccount.setAmount(moneyAccount.getAmount().add(expenseRecord.getSum()));
		recordRepository.deleteById(id);
	}

	public ExpenseRecord enrichExpenseRecord(ExpenseRecord expenseRecord, ExpenseRecordDTO expenseRecordDTO) {
		expenseRecord.setDateOperation(LocalDateTime.now());
		expenseRecord.setAccount(moneyAccountService.getByName(expenseRecordDTO.getNameAccount()).get());
		expenseRecord.setCategory(categoryService.getByName(expenseRecordDTO.getNameCategory()).get());
		return expenseRecord;
	}

	public BigDecimal subtractValue(MoneyAccount account, ExpenseRecordDTO dto) {
		return account.getAmount().subtract(dto.getSum()).setScale(2, RoundingMode.HALF_EVEN);
	}

}
