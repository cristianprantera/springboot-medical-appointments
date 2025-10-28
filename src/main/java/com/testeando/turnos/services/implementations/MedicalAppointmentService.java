package com.testeando.turnos.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.testeando.turnos.models.dtos.MedicalAppointmentRequestDTO;
import com.testeando.turnos.models.dtos.MedicalAppointmentResponseDTO;
import com.testeando.turnos.models.dtos.PatientResponseDTO;
import com.testeando.turnos.models.entities.Doctor;
import com.testeando.turnos.models.entities.DoctorAvailability;
import com.testeando.turnos.models.entities.MedicalAppointment;
import com.testeando.turnos.models.entities.Patient;
import com.testeando.turnos.models.entities.Place;
import com.testeando.turnos.models.entities.Servicio;
import com.testeando.turnos.repositories.IDoctorRepository;
import com.testeando.turnos.repositories.IMedicalAppointmentRepository;
import com.testeando.turnos.repositories.IPatientRepository;
import com.testeando.turnos.repositories.IPlaceRepository;
import com.testeando.turnos.repositories.IServicioRepository;
import com.testeando.turnos.services.interfaces.IMedicalAppointmentService;
import com.testeando.turnos.repositories.IDoctorAvailabilityRepository;

import jakarta.persistence.EntityNotFoundException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MedicalAppointmentService implements IMedicalAppointmentService {

    private final IPatientRepository patientRepository;
    private final IDoctorRepository doctorRepository;
    private final IServicioRepository servicioRepository;
    private final IPlaceRepository placeRepository;
    private final IMedicalAppointmentRepository medicalAppointmentRepository;
    private final IDoctorAvailabilityRepository doctorAvailabilityRepository;
    private final ModelMapper modelMapper;

    public MedicalAppointmentService(IPatientRepository patientRepository, IDoctorRepository doctorRepository,
                                     IMedicalAppointmentRepository medicalAppointmentRepository, ModelMapper modelMapper,
                                     IServicioRepository servicioRepository, IPlaceRepository placeRepository,
                                     IDoctorAvailabilityRepository doctorAvailabilityRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.servicioRepository = servicioRepository;
        this.placeRepository = placeRepository;
        this.medicalAppointmentRepository = medicalAppointmentRepository;
        this.modelMapper = modelMapper;
        this.doctorAvailabilityRepository = doctorAvailabilityRepository;
    }

    @Override
    public MedicalAppointmentResponseDTO createMedicalAppointment(MedicalAppointmentRequestDTO requestDTO) {
        Doctor doctorExisting = doctorRepository.findById(requestDTO.getDoctorId())
                .orElseThrow(() -> new EntityNotFoundException("Doctor no encontrado con ID: " + requestDTO.getDoctorId()));

        Patient patientExisting = patientRepository.findById(requestDTO.getPatientId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con ID: " + requestDTO.getPatientId()));

        Servicio serviceExisting = servicioRepository.findById(requestDTO.getServiceId())
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrado con ID: " + requestDTO.getServiceId()));

        Place placeExisting = placeRepository.findById(requestDTO.getPlaceId())
                .orElseThrow(() -> new EntityNotFoundException("Lugar no encontrado con ID: " + requestDTO.getPlaceId()));

        MedicalAppointment appointment = new MedicalAppointment();
        appointment.setDoctor(doctorExisting);
        appointment.setPatient(patientExisting);
        appointment.setService(serviceExisting);
        appointment.setPlace(placeExisting);
        appointment.setDay(requestDTO.getDay());
        appointment.setHour(requestDTO.getHour());
        
        if(medicalAppointmentRepository.existsByPatientIdAndDayAndHour(appointment.getPatient().getId(), appointment.getDay(), appointment.getHour())) {
            throw new IllegalStateException("El paciente ya tiene un turno en ese horario.");
        }

        MedicalAppointment savedAppointment = medicalAppointmentRepository.save(appointment);

        MedicalAppointmentResponseDTO response = new MedicalAppointmentResponseDTO();
        response.setId(savedAppointment.getIdAppointment());
        response.setDoctorName(savedAppointment.getDoctor().getFirstName() + " " + savedAppointment.getDoctor().getLastName());
        response.setDoctorSpecialty(savedAppointment.getDoctor().getSpecialty().getName());
        response.setPatientName(savedAppointment.getPatient().getFirstName() + " " + savedAppointment.getPatient().getLastName());
        response.setPatientDni(String.valueOf(savedAppointment.getPatient().getDni()));
        response.setPlaceName(savedAppointment.getPlace().getName());
        response.setServiceName(savedAppointment.getService().getName());
        response.setDay(savedAppointment.getDay());
        response.setHour(savedAppointment.getHour());

        return response;
    }

    @Override
    public List<String> getAvailableHours(int doctorId, String dayString) {
        LocalDate day = LocalDate.parse(dayString);
        java.time.DayOfWeek dow = day.getDayOfWeek();

        List<DoctorAvailability> availabilities = doctorAvailabilityRepository.findByDoctorIdAndDay(doctorId, dow);
        List<String> availableHours = new ArrayList<>();

        for (DoctorAvailability avail : availabilities) {
            LocalTime start = avail.getStartHour();
            LocalTime end = avail.getFinishHour();
            int duration = avail.getServicio().getDurationService();

            while (!start.plusMinutes(duration).isAfter(end)) {
                boolean isTaken = medicalAppointmentRepository.existsByDoctorIdAndDayAndHour(doctorId, day, start);
                if (!isTaken) {
                    availableHours.add(start.toString());
                }
                start = start.plusMinutes(duration);
            }
        }
        return availableHours;
    }


	
	@Override
	public List<MedicalAppointmentResponseDTO>  findAllAppointments(){
		List<MedicalAppointment> list= medicalAppointmentRepository.findAll();
		List<MedicalAppointmentResponseDTO> response=new ArrayList<>();
		
		
		
		for(MedicalAppointment l : list) {
		    MedicalAppointmentResponseDTO dto = new MedicalAppointmentResponseDTO();
		    
		    dto.setId(l.getIdAppointment());
		    dto.setDoctorName(l.getDoctor().getFirstName() + " " + l.getDoctor().getLastName());
		    dto.setDoctorSpecialty(l.getDoctor().getSpecialty().getName());
		    dto.setPatientName(l.getPatient().getFirstName() + " " + l.getPatient().getLastName());
		    dto.setPatientDni(String.valueOf(l.getPatient().getDni()));
		    dto.setPlaceName(l.getPlace().getName());
		    dto.setServiceName(l.getService().getName());
		    dto.setDay(l.getDay());
		    dto.setHour(l.getHour());
		    
		    response.add(dto);
		}
		
		return response;		
	}
	
	
	@Override
	public List<String> getAvailableDays(Long doctorId) {
	    List<DoctorAvailability> availabilities = doctorAvailabilityRepository.findByDoctorId(doctorId);
	    List<String> availableDays = new ArrayList<>();
	    LocalDate today = LocalDate.now();

	    for (int i = 0; i < 30; i++) {
	        LocalDate date = today.plusDays(i);
	        DayOfWeek day = date.getDayOfWeek();

	        boolean worksThatDay = availabilities.stream()
	                .anyMatch(a -> a.getDay().equals(day));

	        if (worksThatDay) {
	            availableDays.add(date.toString()); // formato "2025-10-18"
	        }
	    }

	    return availableDays;
	}

	@Override
	public Collection<Doctor> getAppointmentsByDoctorAndDate(Doctor doctor, LocalDate localDate) {
		return null;
	}
	
	@Override
	public List<MedicalAppointmentResponseDTO> findAppointmentsByDoctor(int doctorId){
		
		List<MedicalAppointment> list= medicalAppointmentRepository.findAllByDoctor_Id(doctorId);
		List<MedicalAppointmentResponseDTO> response= new ArrayList<>();
		
		for(MedicalAppointment l : list) {
		    MedicalAppointmentResponseDTO dto = new MedicalAppointmentResponseDTO();
		    
		    dto.setId(l.getIdAppointment());
		    dto.setDoctorName(l.getDoctor().getFirstName() + " " + l.getDoctor().getLastName());
		    dto.setDoctorSpecialty(l.getDoctor().getSpecialty().getName());
		    dto.setPatientName(l.getPatient().getFirstName() + " " + l.getPatient().getLastName());
		    dto.setPatientDni(String.valueOf(l.getPatient().getDni()));
		    dto.setPlaceName(l.getPlace().getName());
		    dto.setServiceName(l.getService().getName());
		    dto.setDay(l.getDay());
		    dto.setHour(l.getHour());
		    
		    response.add(dto);
		}
		
		return response;
		
	}
	
	@Override
	public List<MedicalAppointmentResponseDTO> findAppointmentsByPatient(int patientId) {
	    List<MedicalAppointment> list = medicalAppointmentRepository.findAllByPatient_Id(patientId);
	    List<MedicalAppointmentResponseDTO> response = new ArrayList<>();

	    for (MedicalAppointment l : list) {
	        MedicalAppointmentResponseDTO dto = new MedicalAppointmentResponseDTO();

	        dto.setId(l.getIdAppointment());
	        dto.setDoctorName(l.getDoctor().getFirstName() + " " + l.getDoctor().getLastName());
	        dto.setDoctorSpecialty(l.getDoctor().getSpecialty().getName());
	        dto.setPatientName(l.getPatient().getFirstName() + " " + l.getPatient().getLastName());
	        dto.setPatientDni(String.valueOf(l.getPatient().getDni()));
	        dto.setPlaceName(l.getPlace().getName());
	        dto.setServiceName(l.getService().getName());
	        dto.setDay(l.getDay());
	        dto.setHour(l.getHour());

	        response.add(dto);
	    }

	    return response;
	}

	
	@Override
	public void deleteById(int id) {
	    MedicalAppointment appointment = medicalAppointmentRepository.findById(id)
	    		.orElseThrow(() -> new EntityNotFoundException("Turno no encontrado con ID: " + id));

	    medicalAppointmentRepository.delete(appointment);
	}

	



}
