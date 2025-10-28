package com.testeando.turnos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.testeando.turnos.models.dtos.DoctorResponseDTO;
import com.testeando.turnos.models.dtos.ServicioResponseDTO;
import com.testeando.turnos.models.entities.Servicio;
import com.testeando.turnos.services.interfaces.IDoctorService;
import com.testeando.turnos.services.interfaces.IMedicalAppointmentService;
import com.testeando.turnos.services.interfaces.IServicioService;

import java.util.List;

@RestController
public class ApiController {

    private final IMedicalAppointmentService medicalAppointmentService;
    private final IDoctorService doctorService;
    private final IServicioService servicioService;

    public ApiController(IMedicalAppointmentService medicalAppointmentService, IDoctorService doctorService, IServicioService servicioService) {
        this.medicalAppointmentService = medicalAppointmentService;
		this.doctorService = doctorService;
		this.servicioService = servicioService;
    }

    @GetMapping("/api/available-hours")
    public List<String> getAvailableHours(@RequestParam int doctorId, @RequestParam String day) {
        return medicalAppointmentService.getAvailableHours(doctorId, day);
    }
    
    @GetMapping("/api/doctors/by-specialty/{idSpecialty}")
    public List<DoctorResponseDTO> getDoctorsBySpecialty(@PathVariable Long idSpecialty) {
        return doctorService.getDoctorsBySpecialty(idSpecialty);
    }
    
    @GetMapping("/api/services/by-specialty/{idSpecialty}")
    public List<ServicioResponseDTO> getServicesBySpecialty(@PathVariable Long idSpecialty) {
        return servicioService.getServicioBySpecialty(idSpecialty)
                .stream().map(ServicioResponseDTO::new).toList();
    }

    @GetMapping("/api/available-days")
    public List<String> getAvailableDays(@RequestParam Long doctorId) {
        return medicalAppointmentService.getAvailableDays(doctorId);
    }


}
