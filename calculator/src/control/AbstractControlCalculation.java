package control;

public abstract class AbstractControlCalculation implements Operators{
	protected static final int maxLenght = 16;
	protected int returnCode;
	protected String textIn;

	public abstract void correctCalculation();

	// Getters & Setters :
	public String getTextIn() {
		return textIn;
	}

	public void setTextIn(String textIn) {
		this.textIn = textIn;
	}

	public int getReturnCode() {
		return returnCode;
	}

	protected void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

}// END PRG
