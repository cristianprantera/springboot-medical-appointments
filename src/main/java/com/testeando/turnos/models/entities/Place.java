package com.testeando.turnos.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter

public class Place {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPlace;
	private String name;
	private String address;
	
	public Place() {
	}


	public Place(String name, String addres) {
		super();
		this.name = name;
		this.address = addres;
	}


	public int getIdPlace() {
		return idPlace;
	}




	public void setIdPlace(int idPlace) {
		this.idPlace = idPlace;
	}


	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}

	public String getAddres() {
		return address;
	}

	public void setAddres(String addres) {
		this.address = addres;
	}
		

}
