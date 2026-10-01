package com.example.ejemplos;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
	
	private double roundIEEE754(double o) {
		return BigDecimal.valueOf(o)
				.setScale(16, RoundingMode.HALF_UP)
				.doubleValue();
	}
}
