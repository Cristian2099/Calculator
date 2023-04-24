package com.iconicshield.calculadora;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.iconicshield.calculadora.service.CalculatorService.*;
import static com.iconicshield.calculadora.service.UtilService.*;
import static com.iconicshield.calculadora.service.ValidationService.*;

import com.iconicshield.calculadora.service.CalculatorService;
import com.iconicshield.calculadora.service.ValidationService;

public class MainActivity extends AppCompatActivity {

    String actualTextReversed = "";
    boolean isSign, isPoint = false;
    String emptyString = "";
    CalculatorService calculator = new CalculatorService();

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
        
        writeInScreen(List.of(btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight,
                btnNine, btnZero, btnPoint, btnAdd, btnSubstract, btnMultiply, btnDivide), txvResult);

        btnDelete.setOnClickListener(view -> System.out.println(RemoveCharTxvResult(txvResult)));

        btnClear.setOnClickListener(view -> deleteAll(txvResult));

        btnEqual.setOnClickListener(view -> System.out.println(realizeOperation(txvResult)));
    }

    /**
     * Descripción: Recibe una lista de botones para agregarle a cada uno la acción de escribir el
     * valor del texto que contienen en un TextView.
     * @param buttons: Lista de botones a los cuales se les agregará la opción de escritura en txvResult.
     * @param txvResult: TextView de destino para escribir el texto de los botones.
     */

    public void writeInScreen(List<Button> buttons, TextView txvResult){
        buttons.forEach(button -> button.setOnClickListener(view -> {
            String actualText = txvResult.getText().toString();
            String buttonText = button.getText().toString();
            isSign = CalculatorService.SIGNS.containsValue(buttonText);
            isPoint = buttonText.contains(pointSymbol);
            if (isValidSymbol(actualText, isSign, isPoint)){
                String newText = actualText + buttonText;
                txvResult.setText(newText);
            }
        }));
    }

    /**
     * Descripción: Toma el texto de un TextView, borra el último caracter y lo vuelve a asignar al
     * TextView.
     * @param txvResult: TextView desde el cual se obtiene el texto inicial y al cual se le asigna
     *                 el nuevo texto que se arroja como resultado.
     */
    public String RemoveCharTxvResult(TextView txvResult){
        String actualText, lastChar;
        actualText = txvResult.getText().toString();

        if (!actualText.isEmpty()){
            lastChar = getLastChar(actualText);
            actualTextReversed = new StringBuilder(actualText).reverse().toString();
            if ((lastChar.contains(plusSignString) || lastChar.contains(multiSignString))){
                lastChar =  actualText.contains(plusSignString) ?
                        plusSignClean : multiSignClean;
            }
            replaceCharInTextView(txvResult, lastChar, emptyString);
        }
        return "Text setted successful.";
    }

    public void deleteAll(TextView txvResult){
        txvResult.setText("");
        existPoint = false;
        existSign = false;
    }

    public void replaceCharInTextView(TextView txvResult, String charToReplace, String newTextToSet){
        newTextToSet = actualTextReversed.replaceFirst(charToReplace, newTextToSet);
        txvResult.setText(new StringBuilder(newTextToSet).reverse());
    }

    public String realizeOperation(TextView txvResult){
        String actualText = txvResult.getText().toString();
        if (actualText.isEmpty()){
            return "Actual text is empty.";
        }

        Map<String, Object> factorsMap = getFactors(actualText);
        if (Objects.equals(factorsMap.get("valid_operation"), true)){
            calculator.setNum1(Double.parseDouble(String.valueOf(factorsMap.get("first_factor"))));
            calculator.setNum2(Double.parseDouble(String.valueOf(factorsMap.get("second_factor"))));
            calculator.setSign(String.valueOf(factorsMap.get("sign")));
            txvResult.setText(String.valueOf(calculator.realizeOperation()));
        }
        return "";
    }


}