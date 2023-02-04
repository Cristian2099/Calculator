package com.iconicshield.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.function.Function;

public class MainActivity extends AppCompatActivity {

    String actualTextReversed = "";
    public static final String SPACE_STRING = " ";
    public static final String EMPTY_STRING = "";
    public static final String PLUS_SIGN_STRING = "+";
    public static final String SUBSTRACT_SIGN_STRING = "-";
    public static final String DIV_SIGN_STRING = "/";
    public static final String PLUS_SIGN_CLEAN = "\\+";
    public static final String MULTI_SIGN_STRING = "*";
    public static final String MULTI_SIGN_CLEAN = "\\*";

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
            String newText = actualText + button.getText();
            txvResult.setText(newText);
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
        if ((actualText.contains(PLUS_SIGN_STRING) || actualText.contains(MULTI_SIGN_STRING))){
            String signClean = actualText.contains(PLUS_SIGN_STRING) ?
                    PLUS_SIGN_CLEAN : MULTI_SIGN_CLEAN;

            if (lastChar.equals(MULTI_SIGN_STRING) || lastChar.equals(PLUS_SIGN_STRING)){
                replaceCharInTextView(txvResult, signClean, EMPTY_STRING);
            }else{
                replaceCharInTextView(txvResult, lastChar, EMPTY_STRING);
            }
        }else {
            replaceCharInTextView(txvResult, lastChar, EMPTY_STRING);
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

    public void realizeOperation(TextView txvResult){
        String actualText = txvResult.getText().toString();
        String actualTextValidated = actualText.isEmpty() ? SPACE_STRING : actualText;

    }
}