package ru.tsarev.HomeAccounting.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ru.tsarev.HomeAccounting.dto.ExpenseRecordDTO;
import ru.tsarev.HomeAccounting.exceptions.CategoryNotFoundException;
import ru.tsarev.HomeAccounting.exceptions.ExpenseRecordNotFoundException;
import ru.tsarev.HomeAccounting.exceptions.MoneyAccountNotFoundException;
import ru.tsarev.HomeAccounting.exceptions.MyErrorResponse;
import ru.tsarev.HomeAccounting.services.ExpenseRecordService;

@RestController
@RequestMapping("/expense-record")
public class ExpenseRecordController {

	private final ExpenseRecordService recordService;

	public ExpenseRecordController(ExpenseRecordService recordService) {
		this.recordService = recordService;
	}

	@PostMapping()
	public ResponseEntity<HttpStatus> createRecord(@RequestBody @Valid ExpenseRecordDTO recordDTO) {
		recordService.save(recordDTO);
		return ResponseEntity.ok(HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<ExpenseRecordDTO>> readRecord() {
		return new ResponseEntity<>(recordService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/account/{id}")
	public ResponseEntity<List<ExpenseRecordDTO>> readRecordOnAccount(@PathVariable int id) {
		return new ResponseEntity<>(recordService.readRecordOnAccount(id), HttpStatus.OK);
	}

	@GetMapping("/category/{id}")
	public ResponseEntity<List<ExpenseRecordDTO>> readRecordOnCategory(@PathVariable int id) {
		return new ResponseEntity<>(recordService.readRecordOnCategory(id), HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<HttpStatus> updateRecord(@PathVariable int id,
			@RequestBody @Valid ExpenseRecordDTO recordDTO) {
		recordService.updateExpenseRecord(id, recordDTO);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteRecord(@PathVariable int id) {
		recordService.deleteExpenseRecord(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@ExceptionHandler
	public ResponseEntity<MyErrorResponse> handleException(ExpenseRecordNotFoundException e) {
		MyErrorResponse er = new MyErrorResponse(e.getMessage());
		return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<MyErrorResponse> handleException(MoneyAccountNotFoundException e) {
		MyErrorResponse er = new MyErrorResponse(e.getMessage());
		return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<MyErrorResponse> handleException(CategoryNotFoundException e) {
		MyErrorResponse er = new MyErrorResponse(e.getMessage());
		return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
	}
}
