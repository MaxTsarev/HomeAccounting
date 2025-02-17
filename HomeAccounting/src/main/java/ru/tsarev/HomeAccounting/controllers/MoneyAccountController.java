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
import ru.tsarev.HomeAccounting.dto.MoneyAccountDTO;
import ru.tsarev.HomeAccounting.exceptions.MoneyAccountNotFoundException;
import ru.tsarev.HomeAccounting.exceptions.MyErrorResponse;
import ru.tsarev.HomeAccounting.services.MoneyAccountService;

@RestController
@RequestMapping("/money-account")
public class MoneyAccountController {

	private final MoneyAccountService accountService;

	public MoneyAccountController(MoneyAccountService accountService) {
		this.accountService = accountService;
	}

	@PostMapping()
	public ResponseEntity<HttpStatus> createMoneyAccount(@RequestBody @Valid MoneyAccountDTO moneyAccountDTO) {
		accountService.save(moneyAccountDTO);
		return ResponseEntity.ok(HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<MoneyAccountDTO>> findAll() {
		return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<HttpStatus> updateMoneyAccount(@PathVariable int id,
			@RequestBody @Valid MoneyAccountDTO accountDTO) {
		accountService.updateMoneyAccount(id, accountDTO);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteMoneyAccount(@PathVariable int id) {
		accountService.deleteMoneyAccount(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@ExceptionHandler
	public ResponseEntity<MyErrorResponse> handleException(MoneyAccountNotFoundException e) {
		MyErrorResponse er = new MyErrorResponse(e.getMessage());
		return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
	}

}
