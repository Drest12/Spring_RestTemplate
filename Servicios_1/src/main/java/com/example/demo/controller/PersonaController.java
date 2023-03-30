package com.example.demo.controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CustomError;
import com.example.demo.model.Persona;
import com.example.demo.service.PersonaService;

@RestController
@RequestMapping("/persona")
public class PersonaController {
	@Autowired
	PersonaService personaService;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleError(HttpServletRequest req, Exception ex) {

		System.out.println(ex.getStackTrace());
		CustomError error = new CustomError(HttpStatus.BAD_REQUEST, ex.getMessage());
		return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
	}

	@GetMapping
	public List<Persona> getProducts() {

		return this.personaService.getPersona();
	}

	@GetMapping("/{id}")
	public Persona getPersona(@PathVariable("id") Long id) {

		return this.personaService.getPersona(id);
	}

	@PostMapping
	public Map<String, String> createPersona(
	    @RequestParam(value = "id") Long id,
	    @RequestParam(value = "nombre") String nombre,
	    @RequestParam(value = "apellido") String apellido,
	    @RequestParam(value = "edad") String edad,
	    @RequestParam(value = "nacionalidad") String nacionalidad) {

	    this.personaService.createPersona(id, nombre, apellido, edad, nacionalidad);
	    Map<String, String> map = new HashMap<>();
	    map.put("status", "Persona creada");
	    return map;
	}

	@PutMapping
	public Persona updatePersona(@RequestBody Persona persona) {

		this.personaService.updatePersona(persona);
		return persona;
	}

	@DeleteMapping("/{id}")
	public Map<String, String> deletePersona(@PathVariable("id") Long id) {
		this.personaService.deletePersona(id);
		Map<String, String> map = new HashMap<>();
		map.put("status", "persona eliminadad");
		return map;
	}

	@PatchMapping("/{id}")
	public @ResponseBody void saveManager(@PathVariable("id") Long id, @RequestBody Map<Object, Object> fields) {

		Persona persona = this.personaService.getPersona(id);

		fields.forEach((k, v) -> {
			Field field = ReflectionUtils.findField(Persona.class, (String) k);
			field.setAccessible(true);
			ReflectionUtils.setField(field, persona, v);
		});

		this.personaService.updatePersona(persona);
	}
}
