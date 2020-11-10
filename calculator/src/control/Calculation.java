package control;

import java.util.ArrayList;

public class Calculation extends AbstractCalculation {
	private static Calculation calculation = null;

	private Calculation() {
		super();
		super.setSplit(new Split());
		reset();
	}

	public static Calculation getInstance() {
		if (calculation == null) {
			calculation = new Calculation();
		}

		return calculation;
	}

	@Override
	public void reset() {
		super.setReturnCode(100);
		super.setResult(0D);
		super.setTextOut("0");
		super.setTextIn("");
		super.setListeContentIn(new ArrayList<String>());
	}

	@Override
	public void calcul() {
		split();

		if (super.returnCode > 99) {
			divisionCalcul();
		}

	}// -

	@Override
	public void split() {
		super.split.setIn(super.textIn);
		super.listeContentIn = super.split.split();
		super.returnCode = super.returnCode;
	}

	/**
	 * Divides the calculation into smaller calculations
	 */
	private void divisionCalcul() {
		if (super.listeContentIn.contains(tabOperator[0]) | super.listeContentIn.contains(tabOperator[1])) {
			listeCreation();
			if (super.returnCode > 99) {
				spliCalculationDivMult();
			}

		} else if (super.listeContentIn.contains(tabOperator[2]) | super.listeContentIn.contains(tabOperator[3])) {
			listeCreation();
			if (super.returnCode > 99) {
				super.result = spliCalculationPlusMinus(super.listeContentIn);
			}
		}
	}// -

	/**
	 * Creation of the list
	 */
	public void listeCreation() {
		ArrayList<String> liste;

		liste = split.split();
		super.listeContentIn.clear();

		super.listeContentIn = liste;
	}

	/**
	 * 
	 * @param liste
	 */
	public double spliCalculationPlusMinus(ArrayList<String> liste) {
		String strOperator;
		double nb2, resultTemp;
		int max;

		resultTemp = Double.valueOf(liste.get(0));
		max = liste.size() - 1;

		// Starting the additions or subtractions
		for (int i = 1; i <= max; i += 2) {

			strOperator = liste.get(i);
			nb2 = Double.valueOf(liste.get(i + 1));

			// Addition or Subtraction
			if (strOperator.contentEquals(tabOperator[2])) {
				resultTemp = subtraction(resultTemp, nb2);

			} else if (strOperator.contentEquals(tabOperator[3])) {
				resultTemp = addition(resultTemp, nb2);
			}

		}

		return resultTemp;
	}// -

	/**
	 * Do the multiplications and divisions
	 */
	public void spliCalculationDivMult() {
		ArrayList<String> list;
		String part, partNext, partBefore, str;
		double nb1, nb2;
		int max;

		list = new ArrayList<String>();
		max = super.listeContentIn.size() - 1;

		list.add(super.listeContentIn.get(0));

		// Starting the multiplications and divisions
		for (int i = 1; i < max; i += 2) {
			part = super.listeContentIn.get(i);
			partNext = super.listeContentIn.get(i + 1);

			partBefore = super.listeContentIn.get(i - 1);

			if (part.equals(tabOperator[0]) == true) {
				// Division
				nb1 = getNb1(list, partBefore);
				nb2 = Double.valueOf(partNext);
				str = String.valueOf(division(nb1, nb2));
				list.remove(list.size() - 1);
				list.add(str);

			} else if (part.equals(tabOperator[1]) == true) {
				// Multiplication
				nb1 = getNb1(list, partBefore);
				nb2 = Double.valueOf(partNext);
				str = String.valueOf(multiplication(nb1, nb2));
				list.remove(list.size() - 1);
				list.add(str);

			} else {
				if (partBeforeVerification(partBefore) == true) {
					// Addition or subtraction
					list.add(part);
					list.add(partNext);
				}
			} // end if analyze part
		}

		if (list.isEmpty() == false) {
			// list only contains numbers or minus or plus
			super.result = spliCalculationPlusMinus(list);
		} else {
			super.returnCode = 21;
		}

	}

	/**
	 * 
	 * @param list
	 * @param partBefore
	 * @return number
	 */
	private double getNb1(ArrayList<String> list, String partBefore) {
		Double nb1;
		int lastIndex;

		lastIndex = list.size() - 1;

		if (list.isEmpty() == false) {
			nb1 = Double.valueOf(list.get(lastIndex));
		} else {
			nb1 = Double.valueOf(partBefore);
		}

		return nb1;
	}

	/**
	 * 
	 * @param partBefore
	 * @return true or false
	 */
	private boolean partBeforeVerification(String partBefore) {
		boolean suitable;

		suitable = false;

		if (partBefore.equals(tabOperator[0]) == false) {
			if (partBefore.equals(tabOperator[1]) == false) {
				suitable = true;
			}
		}

		return suitable;
	}

	// Basic operations :
	@Override
	public double addition(double nb1, double nb2) {
		double nb;

		nb = 0;

		if (nb1 > 0D) {
			if (nb2 > 0D) {
				nb = nb1 + nb2;
			}
		}

		return nb;
	}

	@Override
	public double subtraction(double nb1, double nb2) {
		double nb;

		nb = 0;

		if (nb1 > 0D) {
			if (nb2 > 0D) {
				nb = nb1 - nb2;
			}
		}

		return nb;
	}

	@Override
	public double multiplication(double nb1, double nb2) {
		double nb;

		nb = 0;

		if (nb1 > 0D) {
			if (nb2 > 0D) {
				nb = nb1 * nb2;
			}
		}

		return nb;
	}

	@Override
	public double division(double nb1, double nb2) {
		double nb;

		nb = 0D;

		if (nb1 != 0D) {
			if (nb2 != 0D) {
				nb = nb1 / nb2;
			} else {
				super.returnCode = 20;
			}

		} else {
			super.returnCode = 20;
		}

		return nb;
	}

}// END PRG
