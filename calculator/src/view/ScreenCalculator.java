package view;

import java.io.IOException;

import control.Calculation;
import control.ControlCalculation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ScreenCalculator extends Application {
	ControlCalculation controlCalculation = ControlCalculation.instance();
	Calculation calculation = Calculation.getInstance();
	boolean errorMessage;
	int returnCode;
	@FXML
	private Label labelResult;

	public void launchwindows(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Parent root;
		Scene scene;

		labelResult = new Label();
		this.returnCode = 100;
		this.errorMessage = false;

		primaryStage.setResizable(false);
		primaryStage.setTitle("Calculator");

		try {
			root = FXMLLoader.load(ScreenCalculator.class.getResource("screen.fxml"));
			scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void CalculationButton(ActionEvent event) {
		String str, message;
		ReturnControl returnControl;

		if (this.errorMessage == false) {
			str = labelResult.getText();
			this.controlCalculation.reset();
			this.controlCalculation.setTextIn(str);
			this.controlCalculation.correctCalculation();
			this.returnCode = this.controlCalculation.getReturnCode();

			returnControl = new ReturnControl(this.returnCode);
			message = returnControl.getMessage();

			if (message.equalsIgnoreCase("Normal process") == true) {
				this.calculation.reset();
				this.calculation.setTextIn(str);
				this.calculation.calcul();
				this.returnCode = this.calculation.getReturnCode();

				str = this.calculation.display();
				labelResult.setText(str);

				// Problem with the calculation
				if (this.returnCode < 100) {
					this.errorMessage = true;
				}

			} else {
				labelResult.setText(message);
				this.errorMessage = true;
			}

		} else {
			this.errorMessage = true;
		}

	}// -

	@FXML
	void resetButton(ActionEvent event) {
		labelResult.setText("");
		this.returnCode = 100;
		this.errorMessage = false;
	}

	@FXML
	void pointButton(ActionEvent event) {
		String str;

		if (this.errorMessage == false) {
			str = labelResult.getText();
			str += ".";
			labelResult.setText(str);
		}
	}

	// NUMBERS :
	@FXML
	void zeroButton(ActionEvent event) {
		String str;

		if (this.errorMessage == false) {
			str = labelResult.getText();
			str += "0";
			labelResult.setText(str);
		}
	}

	@FXML
	void oneButton(ActionEvent event) {
		String str;

		if (this.errorMessage == false) {
			str = labelResult.getText();
			str += "1";
			labelResult.setText(str);
		}
	}

	@FXML
	void twoButton(ActionEvent event) {
		String str;

		if (this.errorMessage == false) {
			str = labelResult.getText();
			str += "2";
			labelResult.setText(str);
		}
	}

	@FXML
	void threeButton(ActionEvent event) {
		String str;

		if (this.errorMessage == false) {
			str = labelResult.getText();
			str += "3";
			labelResult.setText(str);
		}
	}

	@FXML
	void fourButton(ActionEvent event) {
		String str;

		if (this.errorMessage == false) {
			str = labelResult.getText();
			str += "4";
			labelResult.setText(str);
		}
	}

	@FXML
	void fiveButton(ActionEvent event) {
		String str;

		if (this.errorMessage == false) {
			str = labelResult.getText();
			str += "5";
			labelResult.setText(str);
		}
	}

	@FXML
	void sixButton(ActionEvent event) {
		String str;

		if (this.errorMessage == false) {
			str = labelResult.getText();
			str += "6";
			labelResult.setText(str);
		}
	}

	@FXML
	void sevenButton(ActionEvent event) {
		String str;

		if (this.errorMessage == false) {
			str = labelResult.getText();
			str += "7";
			labelResult.setText(str);
		}
	}

	@FXML
	void heightButton(ActionEvent event) {
		String str;

		if (this.errorMessage == false) {
			str = labelResult.getText();
			str += "8";
			labelResult.setText(str);
		}
	}

	@FXML
	void nineButton(ActionEvent event) {
		String str;

		if (this.errorMessage == false) {
			str = labelResult.getText();
			str += "9";
			labelResult.setText(str);
		}
	}

	// OPERATORS :
	@FXML
	void minusButton(ActionEvent event) {
		String str;

		if (this.errorMessage == false) {
			str = labelResult.getText();
			str += "-";
			labelResult.setText(str);
		}
	}

	@FXML
	void multiplyButton(ActionEvent event) {
		String str;

		if (this.errorMessage == false) {
			str = labelResult.getText();
			str += "x";
			labelResult.setText(str);
		}
	}

	@FXML
	void plusButton(ActionEvent event) {
		String str;

		if (this.errorMessage == false) {
			str = labelResult.getText();
			str += "+";
			labelResult.setText(str);
		}
	}

	@FXML
	void divisionButton(ActionEvent event) {
		String str;

		if (this.errorMessage == false) {
			str = labelResult.getText();
			str += "/";
			labelResult.setText(str);
		}
	}

}// END PRG
