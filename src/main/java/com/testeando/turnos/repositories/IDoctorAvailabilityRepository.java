package com.testeando.turnos.repositories;

import java.time.DayOfWeek;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.testeando.turnos.models.entities.DoctorAvailability;

public interface IDoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Long> {
	
    List<DoctorAvailability> findByDoctorIdAndDay(int doctorId, DayOfWeek day);
    List<DoctorAvailability> findByDoctorId(Long doctorId);

}
