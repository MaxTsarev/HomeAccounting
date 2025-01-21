package ru.tsarev.HomeAccounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.tsarev.HomeAccounting.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role getByName(String name);
}
