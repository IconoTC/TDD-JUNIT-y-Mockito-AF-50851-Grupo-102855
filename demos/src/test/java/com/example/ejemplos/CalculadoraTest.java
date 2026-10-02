package com.example.ejemplos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.ValueSources;

import com.example.test.anotations.Smoke;
import com.example.test.anotations.UnitTest;
import com.example.test.utils.PrivateMethod;

@DisplayName("Pruebas de la clase Calculadora")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
	@Order(10)
	class Suma {
		@Nested
		@DisplayName("Casos validos")
		@Order(10)
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
		@Order(20)
		class KO {
			@Test
			@DisplayName("Arregla el problema con el IEEE754")
			@Order(10)
			void testSumaKO() {
//				var fixure = new Calculadora();

				var actual = fixure.suma(0.1, 0.2);

				assertEquals(0.3, actual);
				assertEquals(0.1, fixure.suma(1, -0.9));
			}

			@ParameterizedTest(name = "{index} => {0} + {1} = {2}")
			@CsvSource({"0.1,0.2,0.3", "1,-0.9,0.1","0,0,0"})
			@DisplayName("Arregla el problema con el IEEE754")
			@Order(20)
			void testSumaParamKO(double operando1, double operando2, double resultado) {
				var actual = fixure.suma(operando1, operando2);

				assertEquals(resultado, actual);
			}
		}
	}
	@Nested
	@DisplayName("Método Divide")
	@Order(40)
	class Divide {
		@Nested
		@DisplayName("Casos validos")
//		@Tag("humo")
		@Smoke()
		@UnitTest()
		@Order(10)
		class OK {
			@Test
			@Order(10)
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
		@Order(20)
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
			//@Disabled("Pendiente de resolver")
			void Divide_por_cero_decimadels() {
				var fixure = new Calculadora();
				ArithmeticException e = assertThrows(ArithmeticException.class, () -> fixure.divide(1.0, 0));
				assertEquals("/ by zero", e.getMessage());
			}
			@Test
			@Order(10)
			@Timeout(unit = TimeUnit.SECONDS, value = 1)
			void lento() {
				var fixure = new Calculadora();

				//var actual = assertTimeout(Duration.ofMillis(90), () -> fixure.divide(1, 2));
				var actual = fixure.lento(1, 2);

				assertEquals(0, actual);

			}
			
		}
	}

	@Nested
	@Order(1)
	class Otros {
		@Test()
		void metodo_privado() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
//			var actual = fixure.roundIEEE754(0.1 + 0.2);
			var actual = PrivateMethod.exec(fixure, "roundIEEE754", new Class[] {double.class}, (0.1 + 0.2));
			
			assertEquals(0.3, actual);
		}
		
//		Reglas para saber si un año es bisiesto
//		Divisible entre 4: Si al dividir el año entre 4 el resultado es exacto (residuo cero), 
//			por regla general es bisiesto.
//		Excepción de los siglos (divisibles entre 100): Si el año termina en doble cero (como 1900 o 2000), 
//			deja de ser bisiesto automáticamente, aunque se pueda dividir entre 4.
//		La contra-excepción (divisibles entre 400): Si ese año de fin de siglo también se puede dividir 
//			entre 400, entonces sí es bisiesto. 
		@Nested
		@Order(1)
		class Bisiesto {
			@ParameterizedTest(name = "El año {0} ES bisiesto")
			@ValueSource(ints = {2024, 2000, 0})
			void ok(int caso) {
				assertTrue(fixure.esBisiesto(caso));
			}
			@ParameterizedTest(name = "El año {0} NO ES bisiesto")
			@ValueSource(ints = {2023, 1900})
			void ko(int caso) {
				assertFalse(fixure.esBisiesto(caso));
			}
		}
	}
}
