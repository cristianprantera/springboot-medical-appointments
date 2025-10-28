package com.testeando.turnos.controllers.views;

import com.testeando.turnos.helpers.ViewRouteHelper;
import com.testeando.turnos.models.dtos.DoctorResponseDTO;
import com.testeando.turnos.repositories.IDoctorRepository;
import com.testeando.turnos.services.interfaces.IDoctorService;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctor")
public class DoctorViewController {
	
	private final IDoctorRepository doctorRepository;
	private final IDoctorService doctorService;
	
	

    public DoctorViewController(IDoctorRepository doctorRepository, IDoctorService doctorService) {
		this.doctorRepository = doctorRepository;
		this.doctorService = doctorService;
	}



	@GetMapping("/index")
    public String showDoctorIndex() {
        return ViewRouteHelper.DOCTOR_INDEX;
    }
    
    
    
    @GetMapping("/profile")
    public String showPatientProfile(Model model,Authentication auth) {
    	String email=auth.getName();
    	
    	var doctor=doctorRepository.findByEmail(email)
    			.orElseThrow(()-> new RuntimeException("No se encontró el paciente con email: "+email));
    	
    	DoctorResponseDTO doctorDTO=doctorService.findDoctorById(doctor.getId());
    	model.addAttribute("doctor",doctorDTO);
    	
    	return ViewRouteHelper.DOCTOR_PROFILE;
    	
    }
}
