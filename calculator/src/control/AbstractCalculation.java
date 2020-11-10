package control;

import java.util.ArrayList;

import view.ReturnControl;

public abstract class AbstractCalculation implements Operators {
	protected String textIn, textOut;
	protected ArrayList<String> listeContentIn;
	protected Split split;
	protected double result;
	protected int returnCode;

	public abstract void calcul();

	public abstract double addition(double nb1, double nb2);

	public abstract double subtraction(double nb1, double nb2);

	public abstract double multiplication(double nb1, double nb2);

	public abstract double division(double nb1, double nb2);

	public abstract void split();

	public abstract void reset();

	public String display() {
		ReturnControl message;
		String str;

		message = new ReturnControl(this.returnCode);
		str = message.getMessage();

		if (str.equalsIgnoreCase("Normal process")) {
			this.textOut = String.valueOf(this.result);

		} else {
			this.textOut = str;
		}

		return this.textOut;
	}

	// Getters & Setters :
	public String getTextIn() {
		return this.textIn;
	}

	public void setTextIn(String textIn) {
		this.textIn = textIn;
	}

	public ArrayList<String> getListeContentIn() {
		return listeContentIn;
	}

	public void setListeContentIn(ArrayList<String> listeContentIn) {
		this.listeContentIn = listeContentIn;
	}

	protected Split getSplit() {
		return split;
	}

	protected void setSplit(Split split) {
		this.split = split;
	}

	protected String getTextOut() {
		return this.textOut;
	}

	protected void setTextOut(String textOut) {
		this.textOut = textOut;
	}

	public double getResult() {
		return this.result;
	}

	protected void setResult(double result) {
		this.result = result;
	}

	public int getReturnCode() {
		return returnCode;
	}

	protected void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

}// END PRG
