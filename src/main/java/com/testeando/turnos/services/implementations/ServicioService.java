package com.testeando.turnos.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.testeando.turnos.models.entities.Servicio;
import com.testeando.turnos.repositories.IServicioRepository;
import com.testeando.turnos.services.interfaces.IServicioService;

@Service
public class ServicioService implements IServicioService {
	
	private final IServicioRepository servicioRepository;

	public ServicioService(IServicioRepository servicioRepository) {
		this.servicioRepository = servicioRepository;
	}
	
	@Override
	public List<Servicio> getServicioBySpecialty(Long idSpecialty){
		return servicioRepository.findBySpecialtyIdSpecialty(idSpecialty);
		
	}


	
	

}
