package com.testeando.turnos.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.testeando.turnos.models.entities.Place;

public interface IPlaceRepository extends JpaRepository<Place,Serializable>{

}
