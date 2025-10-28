package com.testeando.turnos.helpers;

public class ViewRouteHelper {
	
	private ViewRouteHelper() {
		
	}
	
	// ===============================
    // AUTH
    // ===============================
    public static final String USER_LOGIN = "auth/login";


    // ===============================
    // ADMIN
    // ===============================
    public static final String ADMIN_INDEX = "admin/index";
    public static final String ADMIN_APPOINTMENTS = "admin/allAppointments";
    public static final String ADMIN_PATIENT_FORM="admin/patient/patient_form";
    public static final String ADMIN_PATIENT_ALL_PATIENTS="admin/patient/patients";
    public static final String ADMIN_PATIENT_EDIT="admin/patient/patient_edit_form";
    public static final String ADMIN_PATIENT_INFO="admin/patient/patient";
    public static final String ADMIN_PATIENT_CONFIRM_DELETE="admin/patient/patient_confirm_delete";
    
    public static final String ADMIN_DOCTOR_INFO="admin/doctor/doctor";
    public static final String ADMIN_DOCTOR_FORM="admin/doctor/doctor-form";
    public static final String ADMIN_DOCTOR_EDIT="admin/doctor/doctor-edit-form";
    public static final String ADMIN_DOCTOR_ALL_DOCTORS="admin/doctor/doctors";
    
    public static final String ADMIN_APPOINTMENT="admin/appointment/medicalAppointment";
    public static final String ADMIN_APPOINTMENT_FORM="admin/appointment/medicalAppointment-form";
    // ===============================
    // DOCTOR
    // ===============================
    public static final String DOCTOR_INDEX = "doctor/index";
    public static final String DOCTOR_APPOINTMENTS = "doctor/allAppointments";
    public static final String DOCTOR_AVAILABILITY="doctor/doctorAvailability";
    public static final String DOCTOR_PROFILE="doctor/profile";

    // ===============================
    // PATIENT
    // ===============================
    public static final String PATIENT_INDEX = "patient/index";
    public static final String PATIENT_APPOINTMENTS = "patient/allAppointments";
    public static final String PATIENT_PROFILE= "patient/profile";
    public static final String PATIENT_APPOINTMENT_FORM="patient/medicalAppointment-form";
    public static final String PATIENT_APPOINTMENT="patient/medicalAppointment";

}
