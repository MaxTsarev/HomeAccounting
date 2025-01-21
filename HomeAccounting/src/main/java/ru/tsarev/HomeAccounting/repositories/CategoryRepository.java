package ru.tsarev.HomeAccounting.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.tsarev.HomeAccounting.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	Optional<Category> getCategoryById(int id);

	Optional<Category> getByName(String name);
}
