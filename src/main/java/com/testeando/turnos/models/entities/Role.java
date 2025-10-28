package com.testeando.turnos.models.entities;

import com.testeando.turnos.models.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleType role;

    // 🔹 Devuelve el nombre del rol en formato que Spring Security espera
    public String getName() {
        return role.getPrefixedName(); // ej: ROLE_ADMIN, ROLE_DOCTOR, ROLE_PATIENT
    }
}
