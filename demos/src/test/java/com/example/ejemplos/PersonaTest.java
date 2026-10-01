package com.example.ejemplos;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PersonaTest {
	@Test
	void create_persona() {
		var fixure = new Persona(1, "Pepito", "Grillo", null);
		
//		assertEquals(new Persona(2, "Pepito", "Grillo", null), fixure);
		assertNotNull(fixure);
		assertAll("Propiedades del objeto",
			() -> assertEquals(1, fixure.getId()),
			() -> assertEquals("Pepito", fixure.getNombre(), "Nombre"),
			() -> assertEquals("Grillo", fixure.getApellidos(), "Apellidos"),
			() -> assertNull(fixure.getFNacimiento())
		);
	}
}
