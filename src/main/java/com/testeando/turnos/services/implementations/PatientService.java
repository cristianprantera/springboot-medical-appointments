package com.testeando.turnos.services.implementations;

import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.testeando.turnos.models.dtos.PatientRequestDTO;
import com.testeando.turnos.models.dtos.PatientResponseDTO;
import com.testeando.turnos.models.entities.ObraSocial;
import com.testeando.turnos.models.entities.Patient;
import com.testeando.turnos.repositories.IObraSocialRepository;
import com.testeando.turnos.repositories.IPatientRepository;
import com.testeando.turnos.services.interfaces.IPatientService;

import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("patientService")
public class PatientService implements IPatientService {
    
    private final IPatientRepository patientRepository;
    private final IObraSocialRepository obraSocialRepository;
    private final ModelMapper modelMapper;
    
    public PatientService(IPatientRepository patientRepository,IObraSocialRepository obraSocialRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.obraSocialRepository=obraSocialRepository;
        this.modelMapper = modelMapper;
    }
    
    @Override
    public PatientResponseDTO findByDni(int dni) {
        // Buscar paciente por DNI
        Patient patient = patientRepository.findByDni(dni)
                .orElseThrow(() -> new EntityNotFoundException("Paciente con dni " + dni + " no encontrado"));
        
        // Mapear los campos simples del paciente
        PatientResponseDTO responseDTO = modelMapper.map(patient, PatientResponseDTO.class);
        
        // Mapear manualmente el nombre de la obra social
        if (patient.getObraSocial() != null) {
            responseDTO.setObraSocialName(patient.getObraSocial().getName());
        }
        
        return responseDTO;
    }
    
    
    @Override
    public PatientResponseDTO create(PatientRequestDTO requestDTO) {
        ObraSocial obraSocial = obraSocialRepository.findById(requestDTO.getObraSocialId())
                .orElseThrow(() -> new EntityNotFoundException("Obra social no encontrada"));

        Patient patient = new Patient(
                requestDTO.getLastname(),
                requestDTO.getFirstname(),
                requestDTO.getEmail(),
                requestDTO.getDni(),
                obraSocial
        );

        Patient saved = patientRepository.save(patient);

        PatientResponseDTO response = modelMapper.map(saved, PatientResponseDTO.class);
        response.setObraSocialName(saved.getObraSocial().getName());

        return response;
    }
    
    @Override
    public List<PatientResponseDTO> findAllPatients() {
    	
        List<Patient> patientsList = patientRepository.findAll();
        List<PatientResponseDTO> responseList = new ArrayList<>();
        
        for (Patient patient : patientsList) {
            // Usar ModelMapper como en findByDni
            PatientResponseDTO dto = modelMapper.map(patient, PatientResponseDTO.class);
            
            // Mapeo manual de obra social
            if (patient.getObraSocial() != null) {
                dto.setObraSocialName(patient.getObraSocial().getName());
            }
            
            responseList.add(dto);
        }
        
        return responseList;
    }
    
    
    @Override
    public PatientResponseDTO updateByDni(int dni, PatientRequestDTO requestDTO) {
        // 1. Buscar el paciente existente por DNI
        Patient existingPatient = patientRepository.findByDni(dni)
            .orElseThrow(() -> new EntityNotFoundException("Paciente con DNI " + dni + " no encontrado"));
        
        // 2. Buscar la obra social
        ObraSocial obraSocial = obraSocialRepository.findById(requestDTO.getObraSocialId())
            .orElseThrow(() -> new EntityNotFoundException("Obra social no encontrada"));

        // 3. Actualizar campos
        existingPatient.setLastName(requestDTO.getLastname());
        existingPatient.setFirstName(requestDTO.getFirstname());
        existingPatient.setDni(requestDTO.getDni()); // Podés cambiar el DNI si querés
        existingPatient.setObraSocial(obraSocial);

        // 4. Guardar
        Patient updated = patientRepository.save(existingPatient);

        // 5. Mapear respuesta
        PatientResponseDTO response = modelMapper.map(updated, PatientResponseDTO.class);
        response.setObraSocialName(updated.getObraSocial().getName());

        return response;
    }

    @Override
    public PatientResponseDTO findById(Long id) {
        Patient patient = patientRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));
        
        PatientResponseDTO response = modelMapper.map(patient, PatientResponseDTO.class);
        if (patient.getObraSocial() != null) {
            response.setObraSocialName(patient.getObraSocial().getName());
        }
        
        return response;
    }
    
    @Override
    public PatientResponseDTO deleteByDni(int dni) {
        Patient existingPatient = patientRepository.findByDni(dni)
                .orElseThrow(() -> new EntityNotFoundException("Paciente con DNI " + dni + " no encontrado"));
        
        PatientResponseDTO response = modelMapper.map(existingPatient, PatientResponseDTO.class);
        
        if (existingPatient.getObraSocial() != null) {
            response.setObraSocialName(existingPatient.getObraSocial().getName());
        }

        patientRepository.delete(existingPatient);
        
        return response;
    }


    
}
