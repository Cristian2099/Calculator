package com.iconicshield.calculadora.service;

import static com.iconicshield.calculadora.service.CalculatorService.SIGNS;
import static com.iconicshield.calculadora.service.CalculatorService.divSignString;
import static com.iconicshield.calculadora.service.CalculatorService.multiSignString;
import static com.iconicshield.calculadora.service.CalculatorService.plusSignString;
import static com.iconicshield.calculadora.service.CalculatorService.pointSymbol;
import static com.iconicshield.calculadora.service.CalculatorService.pointSymbolClean;
import static com.iconicshield.calculadora.service.CalculatorService.substractSignString;
import static com.iconicshield.calculadora.service.UtilService.getFactors;
import static com.iconicshield.calculadora.service.UtilService.getLastChar;

import com.iconicshield.calculadora.MainActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ValidationService {

    public static boolean existSign, existPoint = false;

    public static boolean isValidSymbol(String actualText, boolean isSign, boolean isPoint){
        if (isSign && isValidSign(actualText, existSign, existPoint)) {
            existSign = true;
            return true;
        } else if (isPoint && isValidPoint(actualText, existSign, existPoint)) {
            existPoint = true;
            return true;
        }else return !isPoint && !isSign;
    }

    public static boolean isValidSign(String actualText, boolean existSign, boolean existPoint){

        if (existPoint && !existSign){
            boolean isLastCharPoint = getLastChar(actualText).equals(pointSymbol);
            if(!isLastCharPoint){
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

        if (SIGNS.containsValue(lastChar) | lastChar.equals(pointSymbol)){
            return false;
        }

        if (!existPoint){
            return true;
        }

        return !(actualText.split(pointSymbolClean).length == 3) && existSign;
    }


}
