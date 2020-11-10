package view;

public class ReturnControl {
	private int code;
	private String message;

	public ReturnControl(int code) {
		setCode(code);
		messageReturn();
	}

	// Getters & Setters
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
		messageReturn();
	}

	public String getMessage() {
		return message;
	}

	/*
	 * Message linked to the return code
	 */
	private void messageReturn() {

		switch (this.code) {
		case 100:
			this.message = "Normal process";
			break;

		case 1:
			this.message = "Unknown or no operator !";
			break;

		case 2:
			this.message = "Problem occured when cutting the operation !";
			break;

		case 10:
			this.message = "Calculation empty !";
			break;

		case 11:
			this.message = "Calculation too long !";
			break;

		case 12:
			this.message = "Problem occured when cutting the operation !";
			break;

		case 13:
			this.message = "Operator next to eachOther in calculation !";
			break;

		case 15:
			this.message = "Operator at the beginning of the calculation !";
			break;

		case 16:
			this.message = "Even calculation !";
			break;

		case 20:
			this.message = "Division by zero !";
			break;

		case 21:
			this.message = "Problem when processing the multiplications and divisions !";
			break;

		default:
			this.message = "Unknown problem !";
		}

	}

}// END PRG
