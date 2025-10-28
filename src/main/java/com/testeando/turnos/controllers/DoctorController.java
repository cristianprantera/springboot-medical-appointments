package com.testeando.turnos.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.testeando.turnos.helpers.ViewRouteHelper;
import com.testeando.turnos.models.dtos.DoctorRequestDTO;
import com.testeando.turnos.models.dtos.DoctorResponseDTO;
import com.testeando.turnos.repositories.ISpecialtyRepository;
import com.testeando.turnos.services.interfaces.IDoctorService;

import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/doctors")
public class DoctorController {
    
    private final IDoctorService doctorService;
    private final ISpecialtyRepository specialtyRepository;

    public DoctorController(IDoctorService doctorService, ISpecialtyRepository specialtyRepository) {
        this.doctorService = doctorService;
        this.specialtyRepository = specialtyRepository;
    }

    // 🩺 MOSTRAR FORMULARIO DE CREACIÓN
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("doctor", new DoctorRequestDTO());
        model.addAttribute("specialties", specialtyRepository.findAll());
        return ViewRouteHelper.ADMIN_DOCTOR_FORM;
    }

    // 💾 CREAR NUEVO MÉDICO
    @PostMapping("/create")
    public String createDoctor(@ModelAttribute("doctor") DoctorRequestDTO doctorDTO, Model model) {
        DoctorResponseDTO doctorResponse = doctorService.createDoctor(doctorDTO);
        model.addAttribute("doctor", doctorResponse);
        return ViewRouteHelper.ADMIN_DOCTOR_INFO;
    }
    
    // 📋 LISTAR TODOS LOS MÉDICOS
    @GetMapping("/allDoctors")
    public String showAllDoctors(Model model) {
        List<DoctorResponseDTO> doctors = doctorService.findAllDoctors();
        model.addAttribute("doctors", doctors);
        return ViewRouteHelper.ADMIN_DOCTOR_ALL_DOCTORS;
    }
    
    // 🔍 VER DETALLES DE UN MÉDICO
    @GetMapping("/{id}")
    public String viewDoctor(@PathVariable int id, Model model) {
        try {
            DoctorResponseDTO doctorDTO = doctorService.findDoctorById(id);
            model.addAttribute("doctor", doctorDTO);
            return ViewRouteHelper.ADMIN_DOCTOR_INFO;
        } catch (EntityNotFoundException e) {
            return "redirect:/doctors/allDoctors?error=not_found";
        }
    }
    
    // ❌ ELIMINAR MÉDICO
    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable int id) {
        try {
            doctorService.deleteDoctor(id);
            return "redirect:/doctors/allDoctors?success=deleted";
        } catch (Exception e) {
            return "redirect:/doctors/allDoctors?error=delete_failed";
        }
    }
    
    // ✏️ ACTUALIZAR MÉDICO
    @PostMapping("/update/{id}")
    public String updateDoctor(@PathVariable int id, @ModelAttribute("doctor") DoctorRequestDTO doctorDTO, Model model) {
        try {
            DoctorResponseDTO doctorResponse = doctorService.updateDoctor(id, doctorDTO);
            model.addAttribute("doctor", doctorResponse);
            return ViewRouteHelper.ADMIN_DOCTOR_INFO;
        } catch (EntityNotFoundException e) {
            return "redirect:/doctors/allDoctors?error=not_found";
        }
    }
    
    // 🧩 MOSTRAR FORMULARIO DE EDICIÓN
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        try {
            DoctorRequestDTO doctorDTO = doctorService.getDoctorRequestDTOById(id);
            DoctorResponseDTO currentDoctor = doctorService.getCurrentDoctorInfo(id);
            
            model.addAttribute("doctor", doctorDTO);
            model.addAttribute("currentDoctor", currentDoctor);
            model.addAttribute("currentIdSpecialty", doctorDTO.getIdSpecialty());
            model.addAttribute("specialties", specialtyRepository.findAll());
            model.addAttribute("doctorId", id);
            return ViewRouteHelper.ADMIN_DOCTOR_EDIT;
        } catch (EntityNotFoundException e) {
            return "redirect:/doctors/allDoctors?error=not_found";
        }
    }

    // 🩻 OBTENER MÉDICOS POR ESPECIALIDAD (para selects dinámicos en AJAX)
    @GetMapping("/bySpecialty/{id}")
    @ResponseBody
    public List<DoctorResponseDTO> getDoctorsBySpecialty(@PathVariable Long id) {
        return doctorService.getDoctorsBySpecialty(id);
    }
}
