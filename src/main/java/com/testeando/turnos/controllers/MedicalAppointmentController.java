package com.testeando.turnos.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;

import com.testeando.turnos.helpers.ViewRouteHelper;
import com.testeando.turnos.models.dtos.MedicalAppointmentRequestDTO;
import com.testeando.turnos.models.dtos.MedicalAppointmentResponseDTO;
import com.testeando.turnos.models.entities.UserEntity;
import com.testeando.turnos.repositories.IDoctorRepository;
import com.testeando.turnos.repositories.IPatientRepository;
import com.testeando.turnos.repositories.IPlaceRepository;
import com.testeando.turnos.repositories.IServicioRepository;
import com.testeando.turnos.repositories.ISpecialtyRepository;
import com.testeando.turnos.repositories.IUserRepository;
import com.testeando.turnos.services.interfaces.IMedicalAppointmentService;

@Controller
@RequestMapping("/medicalAppointments")
public class MedicalAppointmentController {

    private final IMedicalAppointmentService medicalAppointmentService;
    private final IDoctorRepository doctorRepository;
    private final IPatientRepository patientRepository;
    private final IPlaceRepository placeRepository;
    private final IServicioRepository servicioRepository;
    private final ISpecialtyRepository specialtyRepository;
    private final IUserRepository userRepository;

    public MedicalAppointmentController(IMedicalAppointmentService medicalAppointmentService,
                                        IPlaceRepository placeRepository,
                                        IServicioRepository servicioRepository,
                                        IDoctorRepository doctorRepository,
                                        IPatientRepository patientRepository, ISpecialtyRepository specialtyRepository, IUserRepository userRepository) {
        this.medicalAppointmentService = medicalAppointmentService;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.placeRepository = placeRepository;
        this.servicioRepository = servicioRepository;
		this.specialtyRepository = specialtyRepository;
		this.userRepository = userRepository;
    }

    @GetMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'PATIENT')")
    public String showCreateForm(Model model, Authentication auth) {
        
        UserEntity user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + auth.getName()));

        // Carga los datos comunes al formulario
        model.addAttribute("medicalAppointment", new MedicalAppointmentRequestDTO());
        model.addAttribute("doctors", doctorRepository.findAll());
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("places", placeRepository.findAll());
        model.addAttribute("services", servicioRepository.findAll());
        model.addAttribute("specialties", specialtyRepository.findAll());

        // Retorna la vista correspondiente según el rol
        if (user.hasRole("ADMIN")) {
            return ViewRouteHelper.ADMIN_APPOINTMENT_FORM;
        } else if (user.hasRole("PATIENT")) {
            return ViewRouteHelper.PATIENT_APPOINTMENT_FORM;
        } else {
            // Por si entra otro rol no autorizado (seguridad extra)
            throw new RuntimeException("No tenés permiso para acceder a este formulario");
        }
    }


    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'PATIENT')")
    public String createMedicalAppointment(@ModelAttribute MedicalAppointmentRequestDTO medicalAppointmentDTO,
                                           Model model,
                                           Authentication auth) {

        UserEntity user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + auth.getName()));

        MedicalAppointmentResponseDTO medicalAppointmentResponse =
                medicalAppointmentService.createMedicalAppointment(medicalAppointmentDTO);
        
        model.addAttribute("medicalAppointment", medicalAppointmentResponse);

        if (user.hasRole("ADMIN")) {
            return ViewRouteHelper.ADMIN_APPOINTMENT;
        } else if (user.hasRole("PATIENT")) {
            return ViewRouteHelper.PATIENT_APPOINTMENT;
        } else {
            throw new RuntimeException("No tenés permiso para crear turnos");
        }
    }

    
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')")
    public String showAppointments(Model model, Authentication auth) {
        UserEntity user = userRepository.findByEmail(auth.getName()).orElseThrow();

        List<MedicalAppointmentResponseDTO> appointments;
        String view;

        if (user.hasRole("ADMIN")) {
            // Admin ve todos los turnos
            appointments = medicalAppointmentService.findAllAppointments();
            view = ViewRouteHelper.ADMIN_APPOINTMENTS;

        } else if (user.hasRole("DOCTOR")) {
            // Doctor ve solo los suyos
            var doctor = doctorRepository.findByEmail(user.getEmail())
                    .orElseThrow(() -> new RuntimeException("No se encontró el doctor con ese email"));
            appointments = medicalAppointmentService.findAppointmentsByDoctor(doctor.getId());
            view = ViewRouteHelper.DOCTOR_APPOINTMENTS;

        } else {
            // Paciente ve solo los suyos
            var patient = patientRepository.findByEmail(user.getEmail())
                    .orElseThrow(() -> new RuntimeException("No se encontró el paciente con ese email"));
            appointments = medicalAppointmentService.findAppointmentsByPatient(patient.getId());
            view = ViewRouteHelper.PATIENT_APPOINTMENTS;
        }

        model.addAttribute("appointments", appointments);
        return view;
    }



    @PostMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable int id) {
        medicalAppointmentService.deleteById(id);
        return "redirect:/medicalAppointments/all"; // ✅ redirige correctamente
    }




}
