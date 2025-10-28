package com.testeando.turnos.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.testeando.turnos.models.entities.Servicio;
import com.testeando.turnos.services.implementations.ServicioService;

@Controller
public class ServicioController {
	
	private final ServicioService servicioService;

	public ServicioController(ServicioService servicioService) {
		this.servicioService = servicioService;
	}
	
	@GetMapping("/bySpecialty/{idSpecialty}")
	@ResponseBody
	public List<Servicio> getServicioBySpecialty(@PathVariable Long idSpecialty){
		return servicioService.getServicioBySpecialty(idSpecialty);
	}
	
}
