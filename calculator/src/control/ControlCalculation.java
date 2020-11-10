package control;

import java.util.ArrayList;

public class ControlCalculation extends AbstractControlCalculation {
	private static ControlCalculation controlCalculation = null;

	private ControlCalculation() {
		super();
		reset();
	}

	public static ControlCalculation instance() {
		if (controlCalculation == null) {
			controlCalculation = new ControlCalculation();
		}

		return controlCalculation;
	}

	public void reset() {
		super.setReturnCode(100);
		super.setTextIn("");
	}

	@Override
	public void correctCalculation() {
		if (super.textIn.isBlank() == false) {
			if (super.textIn.length() <= maxLenght) {
				// Inspecting the correct writing of the calculation
				correctCalculationOperator();
				if (super.returnCode > 99) {
					evenCalculation();
					if (super.returnCode > 99) {
						nonCorrectPlacedOperator();
					}
				}

			} else {
				super.returnCode = 11;
			}

		} else {
			super.returnCode = 10;
		}
	}

	/**
	 * The calculation should not be even
	 */
	public void evenCalculation() {
		Split split;
		ArrayList<String> liste;
		Boolean end;
		int index;

		split = new Split();
		split.setIn(super.textIn);
		liste = split.split();
		super.returnCode = split.getReturnCode();

		if (super.returnCode > 99) {
			if (liste.isEmpty() == true) {
				super.returnCode = 16;

			} else {
				// Checking if there is no block operator and number at the end
				index = 0;
				do {
					end = false;

					if (liste.get(liste.size() - 1).contains(tabOperator[index])) {
						super.returnCode = 16;
						end = true;
					} else if (index >= tabOperator.length - 1) {
						end = true;
					} else {
						index++;
					}

				} while (end == false);

			}
		}

	}// -

	/**
	 * Check if there is an operator at the start of the calculation or at the end
	 */
	public void nonCorrectPlacedOperator() {
		String str;
		int index, max;
		boolean end;

		index = 0;
		max = tabOperator.length - 1;

		// Checking if the calculation do not start with an operator
		do {
			end = false;
			str = tabOperator[index];

			if (super.textIn.substring(0, 1).equalsIgnoreCase(str)) {
				end = true;
				super.returnCode = 15;

			} else if (index >= max) {
				end = true;

			} else {
				index++;
			}
		} while (end == false);

	}// -

	/**
	 * Check if there are no problem with the operators
	 */
	public void correctCalculationOperator() {
		boolean containOperator, end;
		int indexOperator;

		containOperator = false;
		indexOperator = 0;

		do {
			end = false;

			if (super.textIn.contains(tabOperator[indexOperator]) == true) {
				end = true;
				containOperator = true;

			} else if (indexOperator >= tabOperator.length - 1) {
				end = true;

			} else {
				indexOperator++;

			}

		} while (end == false);

		if (containOperator == true) {
			// Contain at least one operator
			correctCalculationMultiOperator();
		} else {
			super.setReturnCode(12);
		}

	}

	/**
	 * Check if there are successions of operators
	 */
	public void correctCalculationMultiOperator() {
		String doubleOperator;
		boolean containMultiOperator, end1, end2;
		int indexOperator1, indexOperator2, max;

		containMultiOperator = false;
		indexOperator1 = 0;
		max = tabOperator.length - 1;

		do {
			end1 = false;
			indexOperator2 = 0;
			doubleOperator = "";

			// Second operator next to the first one (e.g +/)
			do {
				end2 = false;
				doubleOperator = tabOperator[indexOperator1] + tabOperator[indexOperator2];

				if (super.textIn.contains(doubleOperator) == true) {
					// Association of two operators or more
					end2 = true;
					containMultiOperator = true;

				} else if (indexOperator2 >= max) {
					// End of the possible associations with this operator
					end2 = true;

				} else {
					indexOperator2++;
				}

			} while (end2 == false);

			if (containMultiOperator == true) {
				end1 = true;

			} else if (indexOperator1 >= max) {
				// End of the possible operators
				end1 = true;

			} else {
				indexOperator1++;

			}

		} while (end1 == false);

		// No problems with multiple operators next to oneAnother or not
		if (containMultiOperator == true) {
			super.setReturnCode(13);
		}

	}

}// END PRG
