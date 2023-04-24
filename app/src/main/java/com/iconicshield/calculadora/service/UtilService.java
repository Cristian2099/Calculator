package com.iconicshield.calculadora.service;

import static com.iconicshield.calculadora.SymbolsEnum.DIV_SIGN_STRING;
import static com.iconicshield.calculadora.SymbolsEnum.MULTI_SIGN_CLEAN;
import static com.iconicshield.calculadora.SymbolsEnum.MULTI_SIGN_STRING;
import static com.iconicshield.calculadora.SymbolsEnum.SUBSTRACT_SIGN_STRING;
import static com.iconicshield.calculadora.service.CalculatorService.divSignString;
import static com.iconicshield.calculadora.service.CalculatorService.multiSignClean;
import static com.iconicshield.calculadora.service.CalculatorService.plusSignClean;
import static com.iconicshield.calculadora.service.CalculatorService.plusSignString;
import static com.iconicshield.calculadora.service.CalculatorService.substractSignString;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UtilService {

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
            return Map.of("first_factor", actualTextDivided.get(0),
                    "second_factor", actualTextDivided.get(1),
                    "sign", actualSign,
                    "valid_operation", true);
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
}
