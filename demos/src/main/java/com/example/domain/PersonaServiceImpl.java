package com.example.domain;

import java.util.List;
import java.util.Optional;

import com.example.ejemplos.Persona;

public class PersonaServiceImpl implements PersonaService {
	private PersonasRepository dao;
	
	public PersonaServiceImpl(PersonasRepository dao) {
		this.dao = dao;
	}

	@Override
	public List<Persona> getAll() {
		return dao.read();
	}

	@Override
	public Optional<Persona> getOne(Integer key) {
		return dao.read(key);
	}

	@Override
	public Persona add(Persona item) {
		if(item == null)
			throw new IllegalArgumentException("El argumento no puede ser nulo.");
		// aplica reglas de negocio
		if(!item.isValid())
			throw new RuntimeException("Datos invalidos");
		if(dao.read(item.getId()).isPresent())
			throw new RuntimeException("Clave duplicada");
		return dao.save(item);
	}

	@Override
	public Persona change(Persona item) {
		if(item == null)
			throw new IllegalArgumentException("El argumento no puede ser nulo.");
		// aplica reglas de negocio
		if(!item.isValid())
			throw new RuntimeException("Datos invalidos");
		if(dao.read(item.getId()).isEmpty())
			throw new RuntimeException("Ya no existen los datos");
		return dao.save(item);
	}

	@Override
	public void delete(Persona item) {
		if(item == null)
			throw new IllegalArgumentException("El argumento no puede ser nulo.");
		// aplica reglas de negocio
		if(dao.read(item.getId()).isEmpty())
			throw new RuntimeException("Ya no existen los datos");
		dao.delete(item);
	}

	@Override
	public void deleteById(Integer key) {
		// aplica reglas de negocio
		deleteById(key);
	}

}
