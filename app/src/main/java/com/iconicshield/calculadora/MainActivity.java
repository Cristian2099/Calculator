package com.iconicshield.calculadora;

import static com.iconicshield.calculadora.SignsEnum.*;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String actualTextReversed = "";
    boolean existSign = false;
    public static final List<String> SIGNS = List.of(PLUS_SIGN_STRING.toString(), SUBSTRACT_SIGN_STRING.toString(),
            MULTI_SIGN_STRING.toString(), DIV_SIGN_STRING.toString());

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

        btnDelete.setOnClickListener(view -> System.out.println(RemoveCharFromResult(txvResult)));

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
            if (actualText.contains(PLUS_SIGN_STRING.toString()) ||
                    actualText.contains(SUBSTRACT_SIGN_STRING.toString()) ||
                    actualText.contains(MULTI_SIGN_STRING.toString()) ||
                    actualText.contains(DIV_SIGN_STRING.toString())){
                existSign = true;
            }
            String buttonText = button.getText().toString();
            if (SIGNS.contains(buttonText) && existSign){
                System.out.println("Sign already exist.");
            }else{
                if (SIGNS.contains(buttonText)){
                    existSign = true;
                }
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
    public String RemoveCharFromResult(TextView txvResult){
        String actualText, lastChar;
        actualText = txvResult.getText().toString();

        if (actualText.isEmpty()){
            return "Actual text is empty.";
        }

        lastChar = getLastChar(actualText);

        actualTextReversed = new StringBuilder(actualText).reverse().toString();
        if ((actualText.contains(PLUS_SIGN_STRING.toString()) || actualText.contains(MULTI_SIGN_STRING.toString()))){
            String signClean = actualText.contains(PLUS_SIGN_STRING.toString()) ?
                    PLUS_SIGN_CLEAN.toString() : MULTI_SIGN_CLEAN.toString();

            if (lastChar.equals(MULTI_SIGN_STRING.toString()) || lastChar.equals(PLUS_SIGN_STRING.toString())){
                replaceCharInTextView(txvResult, signClean, EMPTY_STRING.toString());
            }else{
                replaceCharInTextView(txvResult, lastChar, EMPTY_STRING.toString());
            }
        }else {
            replaceCharInTextView(txvResult, lastChar, EMPTY_STRING.toString());
        }
        return "Text setted successful.";
    }

    public void deleteAll(TextView txvResult){
        txvResult.setText("");
    }

    public String getLastChar(String string){
        return String.valueOf(string.charAt(string.length() -1));
    }

    public void replaceCharInTextView(TextView txvResult, String charToReplace, String newTextToSet){
        newTextToSet = actualTextReversed.replaceFirst(charToReplace, newTextToSet);
        txvResult.setText(new StringBuilder(newTextToSet).reverse());
    }

    public String realizeOperation(TextView txvResult){
        String actualText = txvResult.getText().toString();
        char sign = '0';
        if (actualText.isEmpty()){
            return "Actual text is empty.";
        }

        char[] actualTextOnArray = actualText.toCharArray();

        for (char c: actualTextOnArray) {
            sign = Character.isDigit(c) ? sign : c;
        }

        String signStr = String.valueOf(sign);

        if (signStr.equals("0")){
            return "Operation not found.";
        }else{
            signStr = signStr.equals(PLUS_SIGN_STRING.toString()) ? PLUS_SIGN_CLEAN.toString() :
                    signStr.equals(MULTI_SIGN_STRING.toString()) ? MULTI_SIGN_CLEAN.toString() :
                            signStr.equals(SUBSTRACT_SIGN_STRING.toString()) ?
                                    SUBSTRACT_SIGN_STRING.toString() : DIV_SIGN_STRING.toString();
        }

        String[] actualTextDivided = actualText.split(signStr);
        return "";
    }
}