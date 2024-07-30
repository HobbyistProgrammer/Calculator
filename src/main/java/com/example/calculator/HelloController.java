package com.example.calculator;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label lblNumberScreen, lblCalculations;
    @FXML
    private Button btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive,
                    btnSix, btnSeven, btnEight, btnNine;

    @FXML
    private Button btnDecimal, btnEqual, btnPlus, btnSubtract, btnMultiply,
                    btnDivision, btnBack, btnClearAll;
    @FXML
    protected void onNumberButtonClick(Event event) {
        Button btn = (Button) event.getSource();
        String id = btn.getId();

        print(id);
    }

    public void print(String message){
        System.out.println(message);
    }
}