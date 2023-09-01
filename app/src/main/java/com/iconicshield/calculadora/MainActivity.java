package com.iconicshield.calculadora;

import static com.iconicshield.calculadora.service.CalculatorService.SIGNS;
import static com.iconicshield.calculadora.service.CalculatorService.multiSignClean;
import static com.iconicshield.calculadora.service.CalculatorService.multiSignString;
import static com.iconicshield.calculadora.service.CalculatorService.plusSignClean;
import static com.iconicshield.calculadora.service.CalculatorService.plusSignString;
import static com.iconicshield.calculadora.service.CalculatorService.pointSymbol;
import static com.iconicshield.calculadora.service.UtilService.FIRST_FACTOR;
import static com.iconicshield.calculadora.service.UtilService.SECOND_FACTOR;
import static com.iconicshield.calculadora.service.UtilService.SIGN;
import static com.iconicshield.calculadora.service.UtilService.VALID_OPERATION;
import static com.iconicshield.calculadora.service.UtilService.getFactors;
import static com.iconicshield.calculadora.service.UtilService.getLastChar;
import static com.iconicshield.calculadora.service.UtilService.replaceCharInTextView;
import static com.iconicshield.calculadora.service.ValidationService.existPoint;
import static com.iconicshield.calculadora.service.ValidationService.existSign;
import static com.iconicshield.calculadora.service.ValidationService.isValidSymbol;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.iconicshield.calculadora.service.CalculatorService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

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

        btnDelete.setOnClickListener(view -> System.out.println(removeCharTxvResult(txvResult)));
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
            isSign = SIGNS.containsValue(buttonText);
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
    public String removeCharTxvResult(TextView txvResult){
        String actualText, lastChar;
        actualText = txvResult.getText().toString();

        if (!actualText.isEmpty()){
            lastChar = getLastChar(actualText);
            if ((lastChar.contains(plusSignString) || lastChar.contains(multiSignString))){
                lastChar =  actualText.contains(plusSignString) ? plusSignClean : multiSignClean;
            }
            replaceCharInTextView(txvResult, actualText,lastChar, emptyString);
        }
        return "Text setted successful.";
    }

    public void deleteAll(TextView txvResult){
        txvResult.setText("");
        existPoint = false;
        existSign = false;
    }

    public String realizeOperation(TextView txvResult){
        String textInScreen = txvResult.getText().toString();
        if (textInScreen.isEmpty()){
            return "No exist text in screen.";
        }

        Map<String, Object> factorsMap = getFactors(textInScreen);
        if (Objects.equals(factorsMap.get(VALID_OPERATION), true)){
            String firstFactorString = String.valueOf(factorsMap.get(FIRST_FACTOR));
            String secondFactor = String.valueOf(factorsMap.get(SECOND_FACTOR));
            Object firstFactorNumber = 0;
            Object secondFactorNumber = 0;
            if (firstFactorString.contains(pointSymbol)) {
                firstFactorNumber = Double.parseDouble(firstFactorString);
            } else {
                firstFactorNumber = Integer.parseInt(firstFactorString);
            }

            if (secondFactor.contains(pointSymbol)) {
                secondFactorNumber = Double.parseDouble(secondFactor);
            } else {
                secondFactorNumber = Integer.parseInt(secondFactor);
            }
            calculator.setSign(String.valueOf(factorsMap.get(SIGN)));
            if (firstFactorNumber instanceof Integer && secondFactorNumber instanceof Integer){
                calculator.setIntNum1((Integer)firstFactorNumber);
                calculator.setIntNum2((Integer) secondFactorNumber);
                txvResult.setText(String.valueOf(calculator.realizeOperationInteger()));
                existSign = false;
                return "Successful Operation";
            }else {
                firstFactorNumber = Double.parseDouble(firstFactorNumber.toString());
                secondFactorNumber = Double.parseDouble(secondFactorNumber.toString());
            }

            calculator.setNum1((Double) firstFactorNumber);
            calculator.setNum2((Double) secondFactorNumber);
            txvResult.setText(String.valueOf(calculator.realizeOperationDouble()));
            existSign = false;
            return "Successful Operation";
        }
        return "This is not valid operation " + factorsMap.get(VALID_OPERATION);
    }


}