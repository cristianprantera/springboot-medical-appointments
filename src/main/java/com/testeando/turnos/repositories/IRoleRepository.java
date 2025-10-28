package com.testeando.turnos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.testeando.turnos.models.entities.Role;
import com.testeando.turnos.models.enums.RoleType;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByRole(RoleType roleType);
}
