package com.iconicshield.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.biometrics.BiometricManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MainActivity extends AppCompatActivity {

    CalculatorCase calculator;
    String textReversed = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnOne = findViewById(R.id.btnOne);
        Button btnTwo = findViewById(R.id.btnTwo);
        Button btnThree = findViewById(R.id.btnThree);
        Button btnFour = findViewById(R.id.btnFour);
        Button btnFive = findViewById(R.id.btnFive);
        Button btnSix = findViewById(R.id.btnSix);
        Button btnSeven = findViewById(R.id.btnSeven);
        Button btnEight = findViewById(R.id.btnEight);
        Button btnNine = findViewById(R.id.btnNine);
        Button btnZero = findViewById(R.id.btnZero);
        Button btnPoint = findViewById(R.id.btnPoint);
        Button btnEqual = findViewById(R.id.btnEqual);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSubstract = findViewById(R.id.btnSubstract);
        Button btnMultiply = findViewById(R.id.btnMulti);
        Button btnDivide = findViewById(R.id.btnDiv);
        Button btnClear = findViewById(R.id.btnClear);
        Button btnDelete = findViewById(R.id.btnDelete);
        TextView txvResult = findViewById(R.id.txvResult);
        calculator = new CalculatorCase(btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven,
                btnEight, btnNine, btnZero, btnPoint, btnEqual, btnAdd, btnSubstract, btnMultiply, btnDivide, txvResult);

        writeInScreen(List.of(btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight,
                btnNine, btnZero, btnPoint, btnAdd, btnSubstract, btnMultiply, btnDivide), txvResult);

        btnDelete.setOnClickListener(view -> {
            deleteChar(txvResult);
        });

        btnClear.setOnClickListener(view -> {
            deleteAll(txvResult);
        });
    }

    public void writeInScreen(List<Button> buttons, TextView txvResult){
        buttons.forEach(button -> {
            button.setOnClickListener(view -> {
                String actualText = txvResult.getText().toString();
                String newText = actualText + button.getText();
                txvResult.setText(newText);
            });
        });
    }

    public void deleteChar(TextView txvResult){
        String actualText = txvResult.getText().toString().isEmpty() ? " " : txvResult.getText().toString();
        StringBuilder stringBuilder = new StringBuilder(actualText);

        textReversed = stringBuilder.reverse().toString();

        String newText;
        if ((actualText.contains("+") || actualText.contains("*"))){
            String charToReplace = actualText.contains("+") ? "\\+" : "\\*";
            if (String.valueOf(actualText.charAt(actualText.length() -1)).equals("*") ||
                    String.valueOf(actualText.charAt(actualText.length() -1)).equals("+")){
                newText = textReversed.replaceFirst(charToReplace, "");
                txvResult.setText(new StringBuilder(newText).reverse());
            }else{
                newText = textReversed.replaceFirst(String.valueOf(actualText.charAt(actualText.length() -1)), "");
                txvResult.setText(new StringBuilder(newText).reverse());
            }

        }else {
            newText = textReversed.replaceFirst(String.valueOf(actualText.charAt(actualText.length() -1)), "");
            txvResult.setText(new StringBuilder(newText).reverse());
        }

    }

    public void deleteAll(TextView txvResult){
        txvResult.setText("");
    }

}