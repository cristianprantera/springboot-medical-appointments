package com.testeando.turnos.models.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public class MedicalAppointmentRequestDTO {
    // ✅ SOLO CAMBIAR de int a Integer
    private Integer doctorId;
    private Integer patientId;
    private Integer placeId;
    private Integer serviceId;
    
    private LocalDate day;
    private LocalTime hour;

    public MedicalAppointmentRequestDTO() {
    }

    public MedicalAppointmentRequestDTO(Integer doctorId, Integer patientId, Integer placeId, 
                                      Integer serviceId, LocalDate day, LocalTime hour) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.placeId = placeId;
        this.serviceId = serviceId;
        this.day = day;
        this.hour = hour;
    }

    // ✅ SOLO CAMBIAR los getters y setters a Integer
    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }
}