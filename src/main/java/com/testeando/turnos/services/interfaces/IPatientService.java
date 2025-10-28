package com.testeando.turnos.services.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.testeando.turnos.models.dtos.PatientRequestDTO;
import com.testeando.turnos.models.dtos.PatientResponseDTO;

public interface IPatientService {
    PatientResponseDTO findByDni(int dni);
    PatientResponseDTO create(PatientRequestDTO requestDTO);
    List<PatientResponseDTO> findAllPatients();
    PatientResponseDTO updateByDni(int dni, PatientRequestDTO requestDTO); // ← NUEVO
    PatientResponseDTO findById(Long id); // Podés dejarlo o quitarlo
    PatientResponseDTO deleteByDni(int dni);
}