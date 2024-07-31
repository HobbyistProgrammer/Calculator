package com.example.calculator;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Arrays;

public class CalculatorController {
    @FXML
    private Label lblNumberScreen, lblCalculations;
    private boolean sessionCleared = false; // Used to clear screen after number press, only after equal calcuation


    /**
     * @author Benton Le
     * @param event the event triggered by the number button click
     *
     * onNumberButtonClick is a function to generate numbers on the Calculation label
     * screen where users will see their inputted numbers, simultaneously clearing the NumberScreen label
     * for further number input.
     *
     */
    @FXML
    protected void onNumberButtonClick(ActionEvent event) {

        if(sessionCleared) {
            lblNumberScreen.setText("");
            lblCalculations.setText("");
            sessionCleared = false;
        }

        Button btn = (Button) event.getSource();
        String id = btn.getId();

        // print(id);
        switch (id) {
            case "btnZero" -> handleNumberInput("0");
            case "btnOne" -> handleNumberInput("1");
            case "btnTwo" -> handleNumberInput("2");
            case "btnThree" -> handleNumberInput("3");
            case "btnFour" -> handleNumberInput("4");
            case "btnFive" -> handleNumberInput("5");
            case "btnSix" -> handleNumberInput("6");
            case "btnSeven" -> handleNumberInput("7");
            case "btnEight" -> handleNumberInput("8");
            case "btnNine" -> handleNumberInput("9");
            case "btnDecimal" -> handleNumberInput(".");
        }
    }

    @FXML
    protected void onOperationsClick(ActionEvent event){

        if (sessionCleared) {
            lblCalculations.setText(lblNumberScreen.getText());
            sessionCleared = false;
        }

        Button btn = (Button) event.getSource();
        String id = btn.getId();

        switch (id) {
            case "btnSubtract" -> handleOperationInput(" - ");
            case "btnPlus" -> handleOperationInput(" + ");
            case "btnMultiply" -> handleOperationInput(" * ");
            case "btnDivision" -> handleOperationInput(" / ");
            case "btnExponent" -> computeExponent();
            case "btnSquareRoot" -> computeSquareRoot();
            case "btnDividedByOne" -> computeReciprocal();
            case "btnNegative" -> toggleNegative();
            case "btnBack" -> {
                String numbers = lblNumberScreen.getText();
                lblNumberScreen.setText(numbers.substring(0, numbers.length() - 1));
            }
            case "btnClearInput" -> {
                lblNumberScreen.setText("");
            }
            case "btnClearAll" -> {
                lblCalculations.setText("");
                lblNumberScreen.setText("");
            }
        }
    }

    public void handleOperationInput(String operation) {
        lblCalculations.setText(lblNumberScreen.getText() + operation);
        lblNumberScreen.setText("");
    }

    /**
     * @author Benton Le
     *
     * <p>
     * onComputerNumbersInput is the operations handeler. This will concatenate all number and operations
     * input into the label lblCalculations text screen.
     * <ul>
     *     <li> *      : will multiply two given mumbers</li>
     *     <li> +      : will add two given numbers</li>
     *     <li> -      : will subtract two given numbers</li>
     *     <li> /      : will divide two given numbers</li>
     *     <li> %      : will get the percentage of a given number (number / 100)</li>
     * </ul>
     *
     */
    @FXML
    protected void onComputeNumbersInput() {
        lblCalculations.setText(lblCalculations.getText() + lblNumberScreen.getText());
        lblNumberScreen.setText("");

        String[] splits = lblCalculations.getText().split(" ");
        print(Arrays.toString(splits));

        try {
            double firstNumber = Double.parseDouble(splits[0]);
            double secondNumber = Double.parseDouble(splits[2]);
            String operation = splits[1];
            double total = 0.0f;

            switch (operation) {
                case "*" -> total = firstNumber * secondNumber;
                case "-" -> total = firstNumber - secondNumber;
                case "+" -> total = firstNumber + secondNumber;
                case "/" -> total = firstNumber / secondNumber;
            }
            lblNumberScreen.setText(String.format("%.2f", total));
            sessionCleared = true;
        } catch (Exception e){
            print("ignoring numbering problem: " + e);
        }

    }

    public void handleNumberInput(String number) {
        lblNumberScreen.setText(lblNumberScreen.getText() + number);
    }

    private void toggleNegative() {
        lblNumberScreen.setText("-" + lblNumberScreen.getText());
    }

    private void computeExponent() {
        lblCalculations.setText(lblNumberScreen.getText() + "^2");
        double givenNumber = Double.parseDouble(lblNumberScreen.getText());
        lblNumberScreen.setText(String.valueOf(givenNumber * givenNumber));
        sessionCleared = true;
    }

    private void computeSquareRoot(){
        lblCalculations.setText("sqrt(" + lblNumberScreen.getText() + ")");
        double givenNumber = Double.parseDouble(lblNumberScreen.getText());
        lblNumberScreen.setText(String.valueOf(Math.sqrt(givenNumber)));
        sessionCleared = true;
    }

    private void computeReciprocal() {
        lblCalculations.setText("1/" + lblNumberScreen.getText() );
        double givenNumber = Double.parseDouble(lblNumberScreen.getText());
        lblNumberScreen.setText(String.valueOf(1 / givenNumber));
        sessionCleared = true;
    }

    /**
     * Prints message to console
     * @param message the message to print
     */
    private void print(String message){
        System.out.println(message);
    }
}