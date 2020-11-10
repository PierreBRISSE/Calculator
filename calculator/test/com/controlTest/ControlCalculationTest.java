package com.controlTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import control.ControlCalculation;

public class ControlCalculationTest {

	private ControlCalculation controlCalculation;

	@DisplayName("init")
	@BeforeEach
	void init() {
		controlCalculation = ControlCalculation.instance();
	}

	@DisplayName("doubleOperatorOK")
	@Test
	void doubleOperatorOK() {
		String operators;

		operators = "+";
		controlCalculation.reset();
		controlCalculation.setTextIn(operators);
		controlCalculation.correctCalculationMultiOperator();

		assertEquals(controlCalculation.getReturnCode(), 100);
	}// -

	@DisplayName("doubleOperatorError")
	@Test
	void doubleOperatorError() {
		String operators;

		operators = "+/";
		controlCalculation.reset();
		controlCalculation.setTextIn(operators);
		controlCalculation.correctCalculationMultiOperator();

		assertEquals(controlCalculation.getReturnCode(), 13);
	}// -

	@DisplayName("operatorOK")
	@Test
	void operatorOK() {
		String operators;

		operators = "x";
		controlCalculation.reset();
		controlCalculation.setTextIn(operators);
		controlCalculation.correctCalculationOperator();

		assertEquals(controlCalculation.getReturnCode(), 100);
	}// -

	@DisplayName("OperatorError")
	@Test
	void operatorError() {
		String operators;

		operators = "";
		controlCalculation.reset();
		controlCalculation.setTextIn(operators);
		controlCalculation.correctCalculationOperator();

		assertEquals(controlCalculation.getReturnCode(), 12);
	}// -

	@DisplayName("calculationOK")
	@Test
	void calculationOK() {
		String operators;

		operators = "1x2";
		controlCalculation.reset();
		controlCalculation.setTextIn(operators);
		controlCalculation.correctCalculation();

		assertEquals(controlCalculation.getReturnCode(), 100);
	}// -

	@DisplayName("calculationErrorEmpty")
	@Test
	void calculationErrorEmpty() {
		String operators;

		operators = "";
		controlCalculation.reset();
		controlCalculation.setTextIn(operators);
		controlCalculation.correctCalculation();

		assertEquals(controlCalculation.getReturnCode(), 10);
	}// -

	@DisplayName("calculationErrorTooLong")
	@Test
	void calculationErrorTooLong() {
		String operators;

		operators = "1111111111111111111111111111111111111111111111111";
		controlCalculation.reset();
		controlCalculation.setTextIn(operators);
		controlCalculation.correctCalculation();

		assertEquals(controlCalculation.getReturnCode(), 11);
	}// -

	@DisplayName("nonCorrectPlacedOperatorStartError")
	@Test
	void nonCorrectPlacedOperatorStartError() {
		String operators;

		operators = "x12x89/7";
		controlCalculation.reset();
		controlCalculation.setTextIn(operators);
		controlCalculation.nonCorrectPlacedOperator();
		System.out.println(controlCalculation.getReturnCode());//TODO

		assertEquals(controlCalculation.getReturnCode(), 15);
	}// -

	@DisplayName("nonCorrectPlacedOperator")
	@Test
	void nonCorrectPlacedOperator() {
		String operators;

		operators = "12+888+888/89";
		controlCalculation.reset();
		controlCalculation.setTextIn(operators);
		controlCalculation.nonCorrectPlacedOperator();

		assertEquals(controlCalculation.getReturnCode(), 100);
	}// -

	@DisplayName("evenCalculation")
	@Test
	void evenCalculation() {
		String operators;

		operators = "12+1+1";
		controlCalculation.reset();
		controlCalculation.setTextIn(operators);
		controlCalculation.evenCalculation();

		assertEquals(controlCalculation.getReturnCode(), 100);
	}// -

	@DisplayName("evenCalculationError")
	@Test
	void evenCalculationError() {
		String operators;

		operators = "12+1+";
		controlCalculation.reset();
		controlCalculation.setTextIn(operators);
		controlCalculation.evenCalculation();

		assertFalse(controlCalculation.getReturnCode() == 100);
	}// -

}// END TEST PRG
