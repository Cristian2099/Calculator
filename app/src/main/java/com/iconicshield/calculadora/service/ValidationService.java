package com.iconicshield.calculadora.service;

import static com.iconicshield.calculadora.service.CalculatorService.SIGNS;
import static com.iconicshield.calculadora.service.CalculatorService.divSignString;
import static com.iconicshield.calculadora.service.CalculatorService.multiSignString;
import static com.iconicshield.calculadora.service.CalculatorService.plusSignString;
import static com.iconicshield.calculadora.service.CalculatorService.pointSymbol;
import static com.iconicshield.calculadora.service.CalculatorService.pointSymbolClean;
import static com.iconicshield.calculadora.service.CalculatorService.substractSignString;
import static com.iconicshield.calculadora.service.UtilService.FIRST_FACTOR;
import static com.iconicshield.calculadora.service.UtilService.VALID_OPERATION;
import static com.iconicshield.calculadora.service.UtilService.getFactors;
import static com.iconicshield.calculadora.service.UtilService.getLastChar;

import com.iconicshield.calculadora.MainActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

    /*
    +
    2+
    2+1
    2+1+
    2.+
     */
    public static boolean isValidSign(String actualText, boolean existSign, boolean existPoint){

        if (actualText.isEmpty()){
            return false;
        }

        Map<String, Object> factors = getFactors(actualText);
        if (Objects.equals(factors.getOrDefault(VALID_OPERATION, false), true)){
            return false;
        }else{
            return !getLastChar(actualText).equals(pointSymbol);
        }
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

        Map<String, Object> factors = getFactors(actualText);
        if (Objects.equals(factors.get(UtilService.VALID_OPERATION), true)){
            String secondFactor = (String)factors.get(UtilService.SECOND_FACTOR);
            return Objects.requireNonNull(secondFactor).split(pointSymbolClean).length < 2 ;
        }
        return !(actualText.split(pointSymbolClean).length == 3) && existSign;
    }


}
