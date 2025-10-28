package com.testeando.turnos.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testeando.turnos.models.dtos.DoctorRequestDTO;
import com.testeando.turnos.models.dtos.DoctorResponseDTO;
import com.testeando.turnos.models.entities.Doctor;
import com.testeando.turnos.models.entities.Specialty;
import com.testeando.turnos.repositories.IDoctorRepository;
import com.testeando.turnos.repositories.ISpecialtyRepository;
import com.testeando.turnos.services.interfaces.IDoctorService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DoctorService implements IDoctorService {
    
    private final IDoctorRepository doctorRepository;
    private final ISpecialtyRepository specialtyRepository;
    
    public DoctorService(IDoctorRepository doctorRepository, ISpecialtyRepository specialtyRepository) {
        this.doctorRepository = doctorRepository;
        this.specialtyRepository = specialtyRepository;
    }
    
    // 🩺 CREAR NUEVO MÉDICO
    @Override
    @Transactional
    public DoctorResponseDTO createDoctor(DoctorRequestDTO doctorRequestDTO) {
        Specialty specialtyExisting = specialtyRepository.findById(doctorRequestDTO.getIdSpecialty())
                .orElseThrow(() -> new EntityNotFoundException("Especialidad no encontrada"));
        
        Doctor doctor = new Doctor();
        doctor.setLastName(doctorRequestDTO.getLastName());
        doctor.setFirstName(doctorRequestDTO.getFirstName());
        doctor.setDni(doctorRequestDTO.getDni());
        doctor.setLicenseNumber(doctorRequestDTO.getLicenseNumber());
        doctor.setSpecialty(specialtyExisting);
        doctor.setEmail(doctorRequestDTO.getEmail()); // ✅ SE GUARDA EL EMAIL

        Doctor savedDoctor = doctorRepository.save(doctor);
        
        return convertToDTO(savedDoctor);
    }
    
    // 📋 LISTAR MÉDICOS
    @Override
    public List<DoctorResponseDTO> findAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    // ✏️ ACTUALIZAR MÉDICO
    @Override
    @Transactional
    public DoctorResponseDTO updateDoctor(int id, DoctorRequestDTO doctorRequestDTO) {
        Doctor existingDoctor = doctorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Médico no encontrado"));
        
        Specialty specialty = specialtyRepository.findById(doctorRequestDTO.getIdSpecialty())
            .orElseThrow(() -> new EntityNotFoundException("Especialidad no encontrada"));
        
        existingDoctor.setFirstName(doctorRequestDTO.getFirstName());
        existingDoctor.setLastName(doctorRequestDTO.getLastName());
        existingDoctor.setDni(doctorRequestDTO.getDni());
        existingDoctor.setLicenseNumber(doctorRequestDTO.getLicenseNumber());
        existingDoctor.setSpecialty(specialty);
        existingDoctor.setEmail(doctorRequestDTO.getEmail()); // ✅ TAMBIÉN LO ACTUALIZA

        Doctor updatedDoctor = doctorRepository.save(existingDoctor);
        
        return convertToDTO(updatedDoctor);
    }
    
    // 🔍 BUSCAR POR ID
    @Override
    public DoctorResponseDTO findDoctorById(int id) {
        Doctor doctor = doctorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Médico no encontrado"));
        return convertToDTO(doctor);
    }
    
    // 🔁 CONVERTIR ENTITY → REQUEST DTO
    @Override
    public DoctorRequestDTO getDoctorRequestDTOById(int id) {
        Doctor doctor = doctorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Médico no encontrado"));
        return convertToRequestDTO(doctor);
    }
    
    // 📄 OBTENER INFO COMPLETA
    @Override
    public DoctorResponseDTO getCurrentDoctorInfo(int id) {
        Doctor doctor = doctorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Médico no encontrado"));
        return convertToDTO(doctor);
    }
    
    // ❌ ELIMINAR
    @Override
    @Transactional
    public void deleteDoctor(int id) {
        if (!doctorRepository.existsById(id)) {
            throw new EntityNotFoundException("Médico no encontrado");
        }
        doctorRepository.deleteById(id);
    }
    
    // 🧩 CONVERTIR ENTITY → RESPONSE DTO
    private DoctorResponseDTO convertToDTO(Doctor doctor) {
        DoctorResponseDTO dto = new DoctorResponseDTO();
        dto.setId(doctor.getId());
        dto.setFirstName(doctor.getFirstName());
        dto.setLastName(doctor.getLastName());
        dto.setDni(doctor.getDni());
        dto.setLicenseNumber(doctor.getLicenseNumber());
        dto.setSpecialtyName(doctor.getSpecialty().getName());
        dto.setEmail(doctor.getEmail());
        return dto;
    }
    
    // 🔄 CONVERTIR ENTITY → REQUEST DTO
    private DoctorRequestDTO convertToRequestDTO(Doctor doctor) {
        DoctorRequestDTO dto = new DoctorRequestDTO();
        dto.setFirstName(doctor.getFirstName());
        dto.setLastName(doctor.getLastName());
        dto.setDni(doctor.getDni());
        dto.setLicenseNumber(doctor.getLicenseNumber());
        dto.setIdSpecialty(doctor.getSpecialty().getIdSpecialty());
        dto.setEmail(doctor.getEmail()); // ✅ AGREGO TAMBIÉN AQUÍ
        return dto;
    }

    // (por compatibilidad, se deja este método)
    @Override
    public DoctorResponseDTO update(DoctorRequestDTO doctorDTO) {
        return null;
    }
    
    // 🔬 FILTRAR POR ESPECIALIDAD
    @Override
    public List<DoctorResponseDTO> getDoctorsBySpecialty(Long idSpecialty) {
        List<Doctor> doctors = doctorRepository.findBySpecialtyIdSpecialty(idSpecialty);
        return doctors.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

}
