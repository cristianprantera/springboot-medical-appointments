package com.testeando.turnos.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;

import com.testeando.turnos.helpers.ViewRouteHelper;
import com.testeando.turnos.models.entities.DoctorAvailability;
import com.testeando.turnos.repositories.IDoctorAvailabilityRepository;
import com.testeando.turnos.repositories.IDoctorRepository;

@Controller
@RequestMapping("/Doctor")
public class DoctorAvailabilityController {
	
	private final IDoctorAvailabilityRepository doctorAvailabilityRepository;
	private final IDoctorRepository doctorRepository;
	
	public DoctorAvailabilityController(IDoctorAvailabilityRepository doctorAvailabilityRepository, IDoctorRepository doctorRepository) {
		this.doctorAvailabilityRepository = doctorAvailabilityRepository;
		this.doctorRepository = doctorRepository;
	}
	
	//Mostrar disponibilidades
	@GetMapping("/Availability")
	public String showAvailabilityByDoctor(Model model, Authentication auth) {
	    // 1️⃣ Obtener el usuario logueado
	    String email = auth.getName();

	    // 2️⃣ Buscar el doctor correspondiente
	    var doctor = doctorRepository.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("No se encontró el doctor con ese email"));

	    // 3️⃣ Buscar sus disponibilidades
	    List<DoctorAvailability> list = doctorAvailabilityRepository.findByDoctorId((long) doctor.getId());

	    // 4️⃣ Pasarlas al modelo
	    model.addAttribute("availabilities", list);
	    return ViewRouteHelper.DOCTOR_AVAILABILITY;
	}

}
