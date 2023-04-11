package com.iconicshield.calculadora.service;

import static com.iconicshield.calculadora.SymbolsEnum.DIV_SIGN_STRING;
import static com.iconicshield.calculadora.SymbolsEnum.MULTI_SIGN_CLEAN;
import static com.iconicshield.calculadora.SymbolsEnum.MULTI_SIGN_STRING;
import static com.iconicshield.calculadora.SymbolsEnum.SUBSTRACT_SIGN_STRING;
import static com.iconicshield.calculadora.service.CalculatorService.plusSignClean;
import static com.iconicshield.calculadora.service.CalculatorService.plusSignString;
import static com.iconicshield.calculadora.service.CalculatorService.substractSignString;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UtilService {

    public static String getLastChar(String string){
        try {
            return String.valueOf(string.charAt(string.length() -1));
        }catch (Exception e){
            return " ";
        }
    }

    public static Map<String, String> getFactors(String actualText){
        List<String> actualTextDivided;
        String actualSign = getSign(actualText);
        actualTextDivided = Arrays.asList(actualText.split(actualSign));
        if (actualTextDivided.size() == 2){
            return Map.of("first_factor", actualTextDivided.get(0), "second_factor", actualTextDivided.get(1), "sign", actualSign);
        }
        return Map.of("first_factor", String.valueOf(0), "second_factor", String.valueOf(0), "sign", substractSignString);
    }

    public static String getSign(String string){
        char sign = '0';
        String signStr = "";
        char[] actualTextOnArray = string.toCharArray();

        for (char c: actualTextOnArray) {
            sign = CalculatorService.SIGNS.containsValue(String.valueOf(c)) ? c : sign;
        }

        signStr = String.valueOf(sign);

        if (signStr.equals("0")){
            return "Operation not found.";
        }else{
            signStr = signStr.equals(plusSignString) ? plusSignClean :
                    signStr.equals(MULTI_SIGN_STRING.toString()) ? MULTI_SIGN_CLEAN.toString() :
                            signStr.equals(SUBSTRACT_SIGN_STRING.toString()) ?
                                    SUBSTRACT_SIGN_STRING.toString() : DIV_SIGN_STRING.toString();
        }

        return signStr;
    }
}
