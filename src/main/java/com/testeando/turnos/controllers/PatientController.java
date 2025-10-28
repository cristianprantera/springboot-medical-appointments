package com.testeando.turnos.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.testeando.turnos.helpers.ViewRouteHelper;
import com.testeando.turnos.models.dtos.PatientRequestDTO;
import com.testeando.turnos.models.dtos.PatientResponseDTO;
import com.testeando.turnos.services.interfaces.IPatientService;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final IPatientService patientService;

    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{dni}")
    public String getPatient(@PathVariable int dni, Model model) {
        PatientResponseDTO patient = patientService.findByDni(dni);
        model.addAttribute("patient", patient);
        return ViewRouteHelper.ADMIN_PATIENT_INFO;
    }

    @PostMapping("/create")
    public String createPatient(@ModelAttribute PatientRequestDTO patientDTO, Model model) {
        PatientResponseDTO created = patientService.create(patientDTO);
        model.addAttribute("patient", created);
        return ViewRouteHelper.ADMIN_PATIENT_INFO;
    }

    @GetMapping("/form")
    public String showCreateForm(Model model) {
        model.addAttribute("patientDTO", new PatientRequestDTO());
        return ViewRouteHelper.ADMIN_PATIENT_FORM;
    }
    
    @GetMapping("/allPatients")
    public String getAllPatients(Model model) {
        List<PatientResponseDTO> patients = patientService.findAllPatients();
        model.addAttribute("patients", patients);
        return ViewRouteHelper.ADMIN_PATIENT_ALL_PATIENTS;
    }

    @GetMapping("/edit/{dni}")
    public String showEditForm(@PathVariable int dni, Model model) {
        PatientResponseDTO patient = patientService.findByDni(dni);
        model.addAttribute("patientDTO", patient);
        model.addAttribute("patientDni", dni);
        return ViewRouteHelper.ADMIN_PATIENT_EDIT;
    }

    @PostMapping("/update/{dni}")
    public String updatePatient(@PathVariable int dni, @ModelAttribute PatientRequestDTO patientDTO, Model model) {
        PatientResponseDTO updated = patientService.updateByDni(dni, patientDTO);
        model.addAttribute("patient", updated);
        return ViewRouteHelper.ADMIN_PATIENT_INFO;
    }

    // FLUJO SIMPLIFICADO DE ELIMINACIÓN

    // Desde la lista, lleva directamente a confirmación con el DNI precargado
    @GetMapping("/delete/{dni}")
    public String showDeleteConfirmation(@PathVariable int dni, Model model) {
        try {
            PatientResponseDTO patient = patientService.findByDni(dni);
            model.addAttribute("patient", patient);
            return ViewRouteHelper.ADMIN_PATIENT_CONFIRM_DELETE;
        } catch (Exception e) {
            model.addAttribute("error", "Paciente no encontrado: " + e.getMessage());
            return "redirect:/patients/allPatients";
        }
    }

    // Procesar la eliminación
    @PostMapping("/delete/{dni}")
    public String deletePatient(@PathVariable int dni, 
                               @RequestParam("confirmDni") int confirmDni,
                               Model model) {
        
        // Validar que el DNI coincida
        if (dni != confirmDni) {
            model.addAttribute("error", "El DNI ingresado no coincide con el paciente seleccionado.");
            try {
                PatientResponseDTO patient = patientService.findByDni(dni);
                model.addAttribute("patient", patient);
            } catch (Exception e) {
                // Si no encuentra el paciente, redirigir a la lista
                return "redirect:/patients/allPatients";
            }
            return ViewRouteHelper.ADMIN_PATIENT_CONFIRM_DELETE;
        }

        try {
            PatientResponseDTO deleted = patientService.deleteByDni(dni);
            model.addAttribute("success", 
                "✅ El paciente " + deleted.getFirstName() + " " + deleted.getLastName() + 
                " fue eliminado exitosamente");
            return "redirect:/patients/allPatients?success=true";
        } catch (Exception e) {
            model.addAttribute("error", "❌ Error al eliminar paciente: " + e.getMessage());
            return "redirect:/patients/allPatients?error=true";
        }
    }
}