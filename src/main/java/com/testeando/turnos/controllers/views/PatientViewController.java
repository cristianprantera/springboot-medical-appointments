package com.testeando.turnos.controllers.views;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.testeando.turnos.helpers.ViewRouteHelper;
import com.testeando.turnos.models.dtos.PatientResponseDTO;
import com.testeando.turnos.repositories.IPatientRepository;
import com.testeando.turnos.services.interfaces.IPatientService;

@Controller
@RequestMapping("/patient")
public class PatientViewController {
	
    private final IPatientService patientService;
    private final IPatientRepository patientRepository;
    
    

    public PatientViewController(IPatientService patientService, IPatientRepository patientRepository) {
		super();
		this.patientService = patientService;
		this.patientRepository = patientRepository;
	}

	@GetMapping("/index")
    public String showPatientIndex() {
        return ViewRouteHelper.PATIENT_INDEX; // carpeta templates/patient/index.html
    }
    
    @GetMapping("/profile")
    public String showPatientProfile(Model model,Authentication auth) {
    	String email=auth.getName();
    	
    	var patient=patientRepository.findByEmail(email)
    			.orElseThrow(()-> new RuntimeException("No se encontró el paciente con email: "+email));
    	
    	PatientResponseDTO patientDTO=patientService.findByDni(patient.getDni());
    	model.addAttribute("patient",patientDTO);
    	
    	return ViewRouteHelper.PATIENT_PROFILE;
    	
    }
    
    
    
    
    
}
