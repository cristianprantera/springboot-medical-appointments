package com.testeando.turnos.services.interfaces;

import java.util.List;

import com.testeando.turnos.models.entities.Servicio;

public interface IServicioService {

	public List<Servicio> getServicioBySpecialty(Long idSpecialty);
}
