package com.controlTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import control.Calculation;

public class CalculationTest {
	private Calculation calculation;

	@DisplayName("init")
	@BeforeEach
	void init() {
		calculation = Calculation.getInstance();
	}

	// Tests addition :
	@DisplayName("Addition")
	@Test
	void addition() {
		double d, r;

		d = 7.0D;
		calculation.reset();

		r = calculation.addition(3, 4);

		assertEquals(r, d);
	}// -

	// Tests subtraction :
	@DisplayName("subtraction")
	@Test
	void subtraction() {
		double d, r;

		d = -1.0D;
		calculation.reset();

		r = calculation.subtraction(3, 4);

		assertEquals(r, d);
	}// -

	// Tests multiplication :
	@DisplayName("multiplication")
	@Test
	void multiplication() {
		double d, r;

		d = 12.0D;
		calculation.reset();

		r = calculation.multiplication(3, 4);

		assertEquals(r, d);
	}// -

	// Tests division :
	@DisplayName("division")
	@Test
	void division() {
		double d, r;

		d = 1.5D;
		calculation.reset();

		r = calculation.division(3, 2);

		assertEquals(r, d);
	}// -

	@DisplayName("division null")
	@Test
	void divisionNull() {

		calculation.reset();

		calculation.division(3, 0);

		assertTrue(calculation.getReturnCode() == 20);
	}// -

	@DisplayName("spliCalculationPlusMinus")
	@Test
	void spliCalculationPlusMinus() {
		ArrayList<String> liste;
		double res;

		liste = new ArrayList<String>();
		liste.add("1000");
		liste.add("+");
		liste.add("7000");
		liste.add("-");
		liste.add("6000");
		liste.add("+");
		liste.add("10");
		liste.add("-");
		liste.add("10");
		liste.add("+");
		liste.add("20");

		calculation.reset();
		res = calculation.spliCalculationPlusMinus(liste);
		System.out.println("Test : " + res);// TODO

		assertTrue(res == 2020.0D);
	}// -

	@DisplayName("spliCalculationDivMult")
	@Test
	void spliCalculationDivMult() {
		ArrayList<String> liste;

		liste = new ArrayList<String>();
		liste.add("1000");
		liste.add("+");
		liste.add("7000");
		liste.add("-");
		liste.add("4000");
		liste.add("/");
		liste.add("8");
		liste.add("x");
		liste.add("4");
		liste.add("+");
		liste.add("2");
		liste.add("x");
		liste.add("5");

		calculation.reset();
		calculation.setListeContentIn(liste);
		calculation.spliCalculationDivMult();

		assertTrue(calculation.getResult() == 6010.0D);
	}// -

}// END TEST PRG
