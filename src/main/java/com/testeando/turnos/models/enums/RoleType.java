package com.testeando.turnos.models.enums;

public enum RoleType {

    ADMIN,
    DOCTOR,
    PATIENT;
	
    public String getPrefixedName() {
        return "ROLE_" + this.name();
    }
}
