package com.example.ejemplos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculadoraTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSuma() {
		var fixure = new Calculadora();
		
		var actual = fixure.suma(1, 2);
		
		assertEquals(3, actual);
	}
	
	@Test
	void testSumaKO() {
		var fixure = new Calculadora();
		
		var actual = fixure.suma(0.1, 0.2);
		
		assertEquals(0.3, actual);
		assertEquals(0.1, fixure.suma(1, -0.9));
	}

}
