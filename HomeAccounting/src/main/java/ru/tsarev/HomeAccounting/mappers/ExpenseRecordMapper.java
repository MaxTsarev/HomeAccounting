package ru.tsarev.HomeAccounting.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import ru.tsarev.HomeAccounting.dto.ExpenseRecordDTO;
import ru.tsarev.HomeAccounting.models.ExpenseRecord;

@Component
public class ExpenseRecordMapper {

	private final ModelMapper modelMapper = new ModelMapper();

	public ExpenseRecord convertToExpenseRecord(ExpenseRecordDTO expenseRecordDTO) {
		return modelMapper.map(expenseRecordDTO, ExpenseRecord.class);
	}

	public ExpenseRecordDTO convertToExpenseRecordDTO(ExpenseRecord expenseRecord) {
		return modelMapper.map(expenseRecord, ExpenseRecordDTO.class);
	}
}
