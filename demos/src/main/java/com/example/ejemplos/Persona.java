package com.example.ejemplos;

import java.time.LocalDate;
import java.util.Objects;

public class Persona {
	private int id;
	private String nombre;
	private String apellidos;
	private LocalDate fNacimiento;
	
	public Persona(int id, String nombre, String apellidos, LocalDate fNacimiento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fNacimiento = fNacimiento;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public LocalDate getFNacimiento() {
		return fNacimiento;
	}
	public void setFNacimiento(LocalDate fNacimiento) {
		this.fNacimiento = fNacimiento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return id == other.id;
	}
	
	public boolean isValid() {
		return nombre != null && !nombre.isBlank() && nombre.length() <= 50;
	}
}
