package com.testeando.turnos.services.interfaces;

import java.util.List;

import com.testeando.turnos.models.dtos.DoctorRequestDTO;
import com.testeando.turnos.models.dtos.DoctorResponseDTO;

public interface IDoctorService {

    DoctorResponseDTO createDoctor(DoctorRequestDTO doctorRequestDTO);
    List<DoctorResponseDTO> findAllDoctors();
    DoctorResponseDTO updateDoctor(int id, DoctorRequestDTO doctorRequestDTO);
    DoctorResponseDTO findDoctorById(int id);
    DoctorRequestDTO getDoctorRequestDTOById(int id);
    DoctorResponseDTO getCurrentDoctorInfo(int id);
    void deleteDoctor(int id);
    DoctorResponseDTO update(DoctorRequestDTO doctorDTO); // Mantener si lo necesitas
	List<DoctorResponseDTO> getDoctorsBySpecialty(Long idSpecialty);

}