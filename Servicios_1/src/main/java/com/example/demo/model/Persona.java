package com.example.demo.model;

public class Persona {

	private Long id;
    private String Nombre;
    private String Apellido;
    private String Edad;
    private String Nacionalidad;
	public Persona() {
		super();
	}
	public Persona(Long id, String nombre, String apellido, String edad, String nacionalidad) {
		super();
		this.id = id;
		Nombre = nombre;
		Apellido = apellido;
		Edad = edad;
		Nacionalidad = nacionalidad;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public String getEdad() {
		return Edad;
	}
	public void setEdad(String edad) {
		Edad = edad;
	}
	public String getNacionalidad() {
		return Nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		Nacionalidad = nacionalidad;
	}
    
    

}
