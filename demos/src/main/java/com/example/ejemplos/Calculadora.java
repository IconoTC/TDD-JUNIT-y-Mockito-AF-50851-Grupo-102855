package com.example.ejemplos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BooleanSupplier;

public class Calculadora {
	public double suma(double a, double b) {
		return roundIEEE754(a + b);
	}
	public double divide(double a, double b) {
		if(b == 0)
			throw new ArithmeticException("/ by zero");
		if(b < 0)
			throw new IllegalArgumentException("no es un parametro valido");
		return roundIEEE754(a / b);
	}
	public int divide(int a, int b) {
//		try {
//			Thread.sleep(10);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return a / b;
	}
	public int lento(int a, int b) {
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return a / b;
	}
	
	private double roundIEEE754(double o) {
		return BigDecimal.valueOf(o)
				.setScale(16, RoundingMode.HALF_UP)
				.doubleValue();
	}
	public boolean esBisiesto(int año) {
//		return switch (año) {
//		case 2024, 2000 -> true;
//		case 2023, 1900 -> false;
//		default -> throw new RuntimeException("caso no contemplado");
//		};
		return esMultiploDe4(año) && noEsMultiploDe100(año) || esMultiploDe400(año);
	}
	private boolean esMultiploDe400(int año) {
		return año % 400 == 0;
	}
	private boolean noEsMultiploDe100(int año) {
		return año % 100 != 0;
	}
	private boolean esMultiploDe4(int año) {
		return año % 4 == 0;
	}
}
