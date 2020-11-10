package com.controlTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import control.Split;

public class SplitTest {
	private Split split;

	@DisplayName("init")
	@BeforeEach
	void init() {
		split = new Split();
	}

	@DisplayName("division")
	@Test
	void division() {
		ArrayList<String> list;

		split.reset();

		list = split.division("1+2+398+4+500", "+");

		assertEquals(5, list.size());
	}

	@DisplayName("splitPlus")
	@Test
	void splitPlus() {
		ArrayList<String> liste;
		String calc;

		split.reset();

		calc = "1+2+3+4+50";
		liste = split.splitPlus(calc);

		assertEquals(9, liste.size());
	}

	@DisplayName("splitMinus")
	@Test
	void splitMinus() {
		ArrayList<String> liste;
		String calc;

		split.reset();

		calc = "1+28989-3+4-50";
		liste = split.splitSubtraction(calc);

		assertEquals(9, liste.size());
	}

	@DisplayName("splitMultiply")
	@Test
	void splitMultiply() {
		ArrayList<String> liste;
		String calc;

		split.reset();

		calc = "28989-3+4-50x4x8";
		liste = split.splitMultiplication(calc);

		assertEquals(11, liste.size());
	}

	@DisplayName("splitDivision")
	@Test
	void splitDivision() {
		ArrayList<String> liste;
		String calc;

		split.reset();

		calc = "289/89-3+4-50x4x8";
		liste = split.splitDivision(calc);

		assertEquals(13, liste.size());
	}

	@DisplayName("splitOperators")
	@Test
	void splitOperators() {
		ArrayList<String> liste;

		split.reset();

		split.setIn("1-3+388-44444444444x8");
		liste = split.split();
		System.out.println(liste.size());// TODO

		assertTrue(liste.size() == 9);
	}

}// END TEST PRG
