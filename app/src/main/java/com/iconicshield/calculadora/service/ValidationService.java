package com.iconicshield.calculadora.service;

import static com.iconicshield.calculadora.service.CalculatorService.divSignString;
import static com.iconicshield.calculadora.service.CalculatorService.multiSignString;
import static com.iconicshield.calculadora.service.CalculatorService.plusSignString;
import static com.iconicshield.calculadora.service.CalculatorService.pointSymbol;
import static com.iconicshield.calculadora.service.CalculatorService.pointSymbolClean;
import static com.iconicshield.calculadora.service.CalculatorService.substractSignString;
import static com.iconicshield.calculadora.service.UtilService.getFactors;
import static com.iconicshield.calculadora.service.UtilService.getLastChar;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ValidationService {

    public static boolean isValidSymbol(String actualText, boolean isSign, boolean isPoint, boolean existSign, boolean existPoint){
        return isSign ? isValidSign(actualText, existSign, existPoint) : isPoint ? isValidPoint(actualText, existSign, existPoint) : true;
    }

    public static boolean isValidSign(String actualText, boolean existSign, boolean existPoint){
        existSign = actualText.contains(plusSignString) ||
                actualText.contains(substractSignString) ||
                actualText.contains(multiSignString) ||
                actualText.contains(divSignString);

        if (existPoint && !existSign){
            boolean isLastCharPoint = getLastChar(actualText).equals(pointSymbol);
            if(!isLastCharPoint){
                existSign = true;
                return true;
            }
        }

        return !existSign;
    }

    public static boolean isValidPoint(String actualText, boolean existSign, boolean existPoint){

        if (actualText.isEmpty()){
            return false;
        }
        String lastChar = getLastChar(actualText);
        if (CalculatorService.SIGNS.containsValue(lastChar) || lastChar.equals(pointSymbol)){
            return false;
        }

        if (existPoint && existSign){
            Map<String, String> factors = getFactors(actualText);
            return !String.valueOf(factors.get("second_factor")).contains(pointSymbol);
        }

        if (actualText.split(pointSymbolClean).length == 2){
            return false;
        }

        existPoint = true;
        return true;
    }


}
