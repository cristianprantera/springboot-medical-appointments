package com.testeando.turnos.config.seeder;

import com.testeando.turnos.models.entities.Role;
import com.testeando.turnos.models.entities.UserEntity;
import com.testeando.turnos.models.enums.RoleType;
import com.testeando.turnos.repositories.IRoleRepository;
import com.testeando.turnos.repositories.IUserRepository;


import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component

public class UsersSeeder implements CommandLineRunner {

    private static final String PASSWORD_GENERIC = "12345";

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;

    public UsersSeeder(IUserRepository userRepository, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadRoles();
        loadUsers();
    }

    private void loadUsers() {
        if (userRepository.count() == 0) {
            loadUserAdmin();
            loadUserDoctor();
            loadUserPatient();
        }
    }

    private void loadUserAdmin() {
        userRepository.save(buildUser(
                "Cristian", "Admin", "admin@gmail.com", PASSWORD_GENERIC, RoleType.ADMIN
        ));
    }

    private void loadUserDoctor() {
        userRepository.save(buildUser(
                "Carlos", "Doctor", "doctor@gmail.com", PASSWORD_GENERIC, RoleType.DOCTOR
        ));
    }

    private void loadUserPatient() {
        userRepository.save(buildUser(
                "Juan", "Paciente", "paciente@gmail.com", PASSWORD_GENERIC, RoleType.PATIENT
        ));
    }

    private UserEntity buildUser(String firstName, String lastName, String email, String password, RoleType roleType) {
        return UserEntity.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(encryptPassword(password))
                .roles(Set.of(roleRepository.findByRole(roleType).get()))
                .build();
    }

    private void loadRoles() {
        if (roleRepository.count() == 0) {
            roleRepository.save(buildRole(RoleType.ADMIN));
            roleRepository.save(buildRole(RoleType.DOCTOR));
            roleRepository.save(buildRole(RoleType.PATIENT));
            System.out.println("✅ Roles creados correctamente");
        }
    }

    private Role buildRole(RoleType roleType) {
        return Role.builder()
                .role(roleType)
                .build();
    }

    private String encryptPassword(String password) {
        return new BCryptPasswordEncoder(7).encode(password);
    }
}
