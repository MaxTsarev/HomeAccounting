package ru.tsarev.HomeAccounting.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.tsarev.HomeAccounting.models.ExpenseRecord;

@Repository
public interface ExpenseRecordRepository extends JpaRepository<ExpenseRecord, Integer> {
	Optional<ExpenseRecord> getExpenseRecordById(int id);
}
