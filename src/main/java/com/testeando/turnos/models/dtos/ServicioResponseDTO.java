package com.testeando.turnos.models.dtos;

import com.testeando.turnos.models.entities.Servicio;

public class ServicioResponseDTO {
    private Long idService;
    private String name;
    private int durationService;

    // constructor
    public ServicioResponseDTO(Servicio s) {
        this.idService = s.getIdService();
        this.name = s.getName();
        this.durationService = s.getDurationService();
    }

	public Long getIdService() {
		return idService;
	}

	public void setIdService(Long idService) {
		this.idService = idService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDurationService() {
		return durationService;
	}

	public void setDurationService(int durationService) {
		this.durationService = durationService;
	}

}
