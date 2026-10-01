package com.example.ejemplos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Pruebas de la clase Calculadora")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CalculadoraTest {
	private Calculadora fixure;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	@BeforeEach
	void setUp() throws Exception {
		fixure = new Calculadora();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	void init_escenario1() {
		
	}

	@Nested
	@DisplayName("Método Suma")
	class Suma {
		@Nested
		@DisplayName("Casos validos")
		class OK {
			@Test
			@DisplayName("Suma dos enteros positivos")
			void testSuma() {
//				var fixure = new Calculadora();

				var actual = fixure.suma(1, 2);

				assertEquals(3, actual);
			}
		}

		@Nested
		@DisplayName("Casos invalidos")
		class KO {
			@Test
			@DisplayName("Arregla el problema con el IEEE754")
			void testSumaKO() {
//				var fixure = new Calculadora();

				var actual = fixure.suma(0.1, 0.2);

				assertEquals(0.3, actual);
				assertEquals(0.1, fixure.suma(1, -0.9));
			}

			@ParameterizedTest(name = "{index} => {0} + {1} = {2}")
			@CsvSource({"0.1,0.2,0.3", "1,-0.9,0.1","0,0,0"})
			@DisplayName("Arregla el problema con el IEEE754")
			void testSumaParamKO(double operando1, double operando2, double resultado) {
				var actual = fixure.suma(operando1, operando2);

				assertEquals(resultado, actual);
			}
		}
	}
	@Nested
	@DisplayName("Método Divide")
	class Divide {
		@Nested
		@DisplayName("Casos validos")
		class OK {
			@Test
			void Divide_dos_enteros() {
				var fixure = new Calculadora();

				var actual = assertTimeout(Duration.ofMillis(90), () -> fixure.divide(1, 2));

				assertEquals(0, actual);
//				assertDoesNotThrow(() -> fixure.divide(1, 2));

			}
			@Test
			void Divide_dos_decimales() {
				var fixure = new Calculadora();

				var actual = fixure.divide(1.0, 2);

				assertEquals(0.5, actual);
			}
		}
		@RepeatedTest(value = 5, name = "{displayName} {currentRepetition}/{totalRepetitions}")
		void repeatedTest(RepetitionInfo repetitionInfo) {
			var actual = assertTimeout(Duration.ofMillis(90), () -> fixure.divide(1, 2));

			assertEquals(0, actual);
		        assertEquals(5, repetitionInfo.getTotalRepetitions());
		}

		@Nested
		@DisplayName("Casos invalidos")
		class KO {
			@Test
			void Divide_por_cero_enteros() {
				var fixure = new Calculadora();
				ArithmeticException e = assertThrows(ArithmeticException.class, () -> fixure.divide(1, 0));
				assertEquals("/ by zero", e.getMessage());
//				try {
//					fixure.divide(1, 0);
//					fail("No lanza la excepcion");
//				} catch (ArithmeticException e) {
//				} catch (Exception e) {
//					fail("Excepcion %s no controlada".formatted(e.getClass().getCanonicalName()));
//				}
//
//				assertEquals(0, actual);
			}
			@Test
			void Divide_por_cero_decimadels() {
				var fixure = new Calculadora();
				ArithmeticException e = assertThrows(ArithmeticException.class, () -> fixure.divide(1.0, 0));
				assertEquals("/ by zero", e.getMessage());
			}
		}
	}
}
