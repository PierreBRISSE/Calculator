package control;

import java.util.ArrayList;

public abstract class AbstractSplit {
	protected String textIn;
	protected int returnCode;

	public abstract ArrayList<String> split();

	//Getters & Setters
	public int getReturnCode() {
		return returnCode;
	}

	protected void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getIn() {
		return this.textIn;
	}

	public void setIn(String in) {
		this.textIn = in;
	}

}// END PRG
