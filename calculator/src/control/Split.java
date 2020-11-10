package control;

import java.util.ArrayList;
import java.util.Iterator;

public class Split extends AbstractSplit implements Operators {

	public Split() {
		super();
		reset();
	}

	public void reset() {
		super.setIn("");
		super.setReturnCode(100);
	}

	@Override
	public ArrayList<String> split() {
		ArrayList<String> listFinal;

		listFinal = new ArrayList<String>();

		if (super.textIn.contains(tabOperator[0])) {
			// Contains /
			listFinal = splitDivision(super.textIn);

		} else if (super.textIn.contains(tabOperator[1])) {
			// Contains x
			listFinal = splitMultiplication(super.textIn);

		} else if (super.textIn.contains(tabOperator[2])) {
			// Contains -
			listFinal = splitSubtraction(super.textIn);

		} else if (super.textIn.contains(tabOperator[3])) {
			// Contains +
			listFinal = splitPlus(super.textIn);

		} else {
			super.returnCode = 1;
		}

		return listFinal;
	}

	/**
	 * 
	 * @param textIn
	 * @return list
	 */
	public ArrayList<String> splitPlus(String textIn) {
		Iterator<String> it;
		ArrayList<String> list, listTemp;
		String delimeter, str;

		delimeter = tabOperator[3];
		listTemp = division(textIn, delimeter);
		it = listTemp.iterator();
		list = new ArrayList<String>();

		while (it.hasNext()) {
			str = it.next();
			if (str.isBlank() == false) {
				// Adding the content in the list
				list.add(str);
				if (it.hasNext()) {
					list.add(delimeter);
				}

			}
		} // end while

		if (list.isEmpty() == true) {
			super.returnCode = 2;
		}

		return list;
	}

	/**
	 * 
	 * @param textIn
	 * @return list
	 */
	public ArrayList<String> splitSubtraction(String textIn) {
		Iterator<String> it, itsplit;
		ArrayList<String> list, listTemp, listSplit;
		String str;

		list = new ArrayList<String>();
		listSplit = division(textIn, tabOperator[2]);
		itsplit = listSplit.iterator();

		while (itsplit.hasNext()) {
			str = itsplit.next();
			if (str.isBlank() == false) {

				if (str.contains(tabOperator[3])) {
					// The text contain a +
					listTemp = new ArrayList<String>();
					listTemp = splitPlus(str);
					it = listTemp.iterator();
					// Adding the number and other operators
					while (it.hasNext()) {
						list.add(it.next());
					}

				} else {
					// The text is a number or a -
					list.add(str);
				}

				if (itsplit.hasNext()) {
					list.add(tabOperator[2]);
				}

			}
		} // end of the loop

		if (list.isEmpty() == true) {
			super.returnCode = 2;
		}

		return list;
	}

	/**
	 * 
	 * @param textIn
	 * @return list
	 */
	public ArrayList<String> splitMultiplication(String textIn) {
		Iterator<String> it, itsplit;
		ArrayList<String> list, listTemp, listSplit;
		String str;

		list = new ArrayList<String>();
		listSplit = division(textIn, tabOperator[1]);
		itsplit = listSplit.iterator();

		while (itsplit.hasNext()) {
			str = itsplit.next();
			if (str.isBlank() == false) {

				if (str.contains(tabOperator[2])) {
					// The text contain a -
					listTemp = splitSubtraction(str);
					it = listTemp.iterator();
					// Adding the number and other operators
					while (it.hasNext()) {
						list.add(it.next());
					}

				} else if (str.contains(tabOperator[3])) {
					listTemp = splitPlus(str);
					it = listTemp.iterator();
					// Adding the number and other operators
					while (it.hasNext()) {
						list.add(it.next());
					}

				} else {
					// The text is a number or a -
					list.add(str);
				}

				if (itsplit.hasNext()) {
					list.add(tabOperator[1]);
				}

			}
		} // end of the loop

		if (list.isEmpty() == true) {
			super.returnCode = 2;
		}

		return list;
	}

	/**
	 * 
	 * @param string
	 * @return ArrayList
	 */
	public ArrayList<String> splitDivision(String textIn) {
		Iterator<String> it, itsplit;
		ArrayList<String> list, listTemp, listSplit;
		String str;

		list = new ArrayList<String>();
		listSplit = division(textIn, tabOperator[0]);
		itsplit = listSplit.iterator();

		while (itsplit.hasNext()) {
			str = itsplit.next();
			if (str.isBlank() == false) {

				if (str.contains(tabOperator[1])) {
					// The text contain a -
					listTemp = splitMultiplication(str);
					it = listTemp.iterator();
					// Adding the number and other operators
					while (it.hasNext()) {
						list.add(it.next());
					}

				} else if (str.contains(tabOperator[2])) {
					listTemp = splitSubtraction(str);
					it = listTemp.iterator();
					// Adding the number and other operators
					while (it.hasNext()) {
						list.add(it.next());
					}

				} else if (str.contains(tabOperator[3])) {
					listTemp = splitPlus(str);
					it = listTemp.iterator();
					// Adding the number and other operators
					while (it.hasNext()) {
						list.add(it.next());
					}

				} else {
					// The text is a number or a /
					list.add(str);
				}

				if (itsplit.hasNext()) {
					list.add(tabOperator[0]);
				}

			}
		} // end of the loop

		if (list.isEmpty() == true) {
			super.returnCode = 2;
		}

		return list;
	}

	/**
	 * 
	 * @param textIn
	 * @param delimeter
	 * @return list
	 */
	public ArrayList<String> division(String textIn, String delimeter) {
		ArrayList<String> list;
		String letter, letterBefore;
		int lastIndex, max;

		list = new ArrayList<String>();
		max = textIn.length() - 1;
		lastIndex = 0;

		// Analyzing the text in to know where are the operators
		for (int i = 1; i < max; i++) {
			letter = textIn.substring(i, i + 1);
			letterBefore = textIn.substring(lastIndex, i);

			if (letter.equals(delimeter)) {
				// Cutting the word by operator
				list.add(letterBefore);
				lastIndex = i + 1;
			}
		}

		list.add(textIn.substring(lastIndex, max + 1));

		return list;
	}

}// END PRG
