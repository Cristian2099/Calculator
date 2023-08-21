package com.iconicshield.calculadora.service;

import static com.iconicshield.calculadora.SymbolsEnum.MULTI_SIGN_STRING;
import static com.iconicshield.calculadora.SymbolsEnum.SUBSTRACT_SIGN_STRING;
import static com.iconicshield.calculadora.service.CalculatorService.divSignString;
import static com.iconicshield.calculadora.service.CalculatorService.multiSignClean;
import static com.iconicshield.calculadora.service.CalculatorService.plusSignClean;
import static com.iconicshield.calculadora.service.CalculatorService.plusSignString;
import static com.iconicshield.calculadora.service.CalculatorService.substractSignString;

import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UtilService {

    public static String FIRST_FACTOR = "first_factor";
    public static String SECOND_FACTOR = "second_factor";
    public static String SIGN = "sign";
    public static String VALID_OPERATION = "valid_operation";

    public static String getLastChar(String string){
        try {
            return String.valueOf(string.charAt(string.length() -1));
        }catch (Exception e){
            return " ";
        }
    }

    public static Map<String, Object> getFactors(String actualText){
        List<String> actualTextDivided;
        String actualSign = getSign(actualText);
        actualTextDivided = Arrays.asList(actualText.split(actualSign));
        if (actualTextDivided.size() == 2){
            return Map.of(FIRST_FACTOR, actualTextDivided.get(0),
                    SECOND_FACTOR, actualTextDivided.get(1),
                    SIGN, actualSign,

                    VALID_OPERATION, true);
        }
        return Map.of("valid_operation", false);
    }

    public static String getSign(String string){
        char sign = '0';
        String signStr = "";
        char[] actualTextOnArray = string.toCharArray();

        for (char c: actualTextOnArray) {
            sign = CalculatorService.SIGNS.containsValue(String.valueOf(c)) ? c : sign;
        }

        signStr = String.valueOf(sign);

        if (!signStr.equals("0")){
            signStr = signStr.equals(plusSignString) ? plusSignClean :
                    signStr.equals(MULTI_SIGN_STRING.toString()) ? multiSignClean :
                            signStr.equals(SUBSTRACT_SIGN_STRING.toString()) ? substractSignString :
                                    divSignString;
        }

        return signStr;
    }

    public static void replaceCharInTextView(TextView txvResult, String actualText, String charToReplace, String newTextToSet){
        String actualTextReversed = new StringBuilder(actualText).reverse().toString();
        newTextToSet = actualTextReversed.replaceFirst(charToReplace, newTextToSet);
        txvResult.setText(new StringBuilder(newTextToSet).reverse());
    }
}
