package com.example.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.model.Persona;





@Service
public class PersonaServiceImpl implements PersonaService {

	List<Persona> persona = new ArrayList<Persona>();
	
    public PersonaServiceImpl() {
    	persona.add(new Persona(Long.valueOf(1), "Nestor", "Atiro","20","Peru"));
    	persona.add(new Persona(Long.valueOf(2), "Andres", "Talla","19","Peru"));
    	persona.add(new Persona(Long.valueOf(3), "Miguel", "Romero","20","Alemania"));
    	persona.add(new Persona(Long.valueOf(4), "Josue", "Noles","19","Portugal"));
    	persona.add(new Persona(Long.valueOf(5), "Sergio", "Danes","20","Estados Unidos"));
    	persona.add(new Persona(Long.valueOf(6), "Alejandro", "Ramos","19","Chile"));

    }
	
	@Override
	public List<Persona> getPersona() {
		
		return this.persona;
	}

	@Override
	public Persona getPersona(Long id) {
	     Iterator<Persona> iterator = persona.iterator();
	        while (iterator.hasNext()) {
	            Persona persona = iterator.next();
	            if (persona.getId().equals(id)) {
	                return persona;
	            }
	        }
	        return null;
	}

	@Override
	public void createPersona(Long id, String nombre, String apellido, String edad, String nacionalidad) {
		  Persona persona = new Persona(id, nombre, apellido,edad,nacionalidad);
	        this.persona.add(persona);

	}

	@Override
	public void updatePersona(Persona persona) {
		getPersona(persona.getId()).setNombre(persona.getNombre());
        getPersona(persona.getId()).setApellido(persona.getApellido());
        getPersona(persona.getId()).setEdad(persona.getEdad());
        getPersona(persona.getId()).setNacionalidad(persona.getNacionalidad());
	}

	@Override
	public void deletePersona(Long id) {
		this.persona.remove(getPersona(id));

	}

}
