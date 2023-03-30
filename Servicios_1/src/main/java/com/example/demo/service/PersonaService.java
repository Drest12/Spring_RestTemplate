package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Persona;



public interface PersonaService {
	// Get Operation
    List<Persona> getPersona();

    // Get Operation
    Persona getPersona(Long id);

    // Post Operation
    void createPersona(Long id, String nombre, String apellido, String edad, String nacionalidad);

    // Put Operation
    void updatePersona(Persona persona);

    // Delete Operation
    void deletePersona(Long id);

	
}
