package com.example.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.ejemplos.Persona;

@ExtendWith(MockitoExtension.class)
class PersonaServiceImplTest {
	@Mock
	PersonasRepository dao;
	@InjectMocks
	PersonaServiceImpl srv;

//	PersonasRepository dao;
//	PersonaService srv;
//
//	@BeforeEach
//	void setUp() throws Exception {
//		dao = mock(PersonasRepository.class);
//		srv = new PersonaServiceImpl(dao);
//	}

	@Test
	void AddOK() {
		when(dao.read(anyInt())).thenReturn(Optional.empty());
		when(dao.save(any(Persona.class))).thenAnswer(answer -> new Persona(1, "Pepito", "Grillo", null));
		var nueva = spy(new Persona(0, "Pepito", "Grillo", null));

		var actual = srv.add(nueva);

		assertNotNull(actual);
		assertAll("Persona", () -> assertEquals(1, actual.getId()),
				() -> assertEquals("Pepito", actual.getNombre(), "nombre"),
				() -> assertEquals("Grillo", actual.getApellidos(), "apellidos"),
				() -> assertNull(actual.getFNacimiento()));
		verify(nueva, times(1)).isValid();
	}

	@Test
	void AddNotNullKO() {

		var ex = assertThrows(IllegalArgumentException.class, () -> srv.add(null));
		assertEquals("El argumento no puede ser nulo.", ex.getMessage());
	}

	@Test
	void AddInvalidDataKO() {
		var nueva = spy(new Persona(0, "x".repeat(51), "Grillo", null));

		var ex = assertThrows(RuntimeException.class, () -> srv.add(nueva));
		assertEquals("Datos invalidos", ex.getMessage());

	}

	@Test
	void AddDuplicateKeyKO() {
		when(dao.read(anyInt())).thenAnswer(answer -> Optional.of(new Persona(0, "xxxx", "xxx", null)));
//		when(dao.save(any(Persona.class))).thenAnswer(answer -> new Persona(1, "Pepito", "Grillo", null));
		var nueva = spy(new Persona(0, "Pepito", "Grillo", null));

		var ex = assertThrows(RuntimeException.class, () -> srv.add(nueva));
		assertEquals("Clave duplicada", ex.getMessage());

		verify(nueva, times(1)).isValid();
		verify(dao, never()).save(nueva);
	}

//	@Test
//	void testChange() {
//		fail("Not yet implemented");
//	}

	@Test
	void testGetAll() {
		List<Persona> lista = List.of(
				new Persona(1, "xxxx", "xxx", null),
				new Persona(2, "xxxx", "xxx", null),
				new Persona(3, "xxxx", "xxx", null),
				new Persona(4, "xxxx", "xxx", null)
				);
		when(dao.read()).thenReturn(lista);
		
		var actual = srv.getAll();
		
		assertEquals(4, actual.size());
		assertIterableEquals(lista, actual);
	}
	
//
//	@Test
//	void testGetOne() {
//		fail("Not yet implemented");
//	}
//
//
//
//	@Test
//	void testDelete() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeleteById() {
//		fail("Not yet implemented");
//	}

}
