package com.iconicshield.calculadora.service;

import static com.iconicshield.calculadora.SymbolsEnum.DIV_SIGN_STRING;
import static com.iconicshield.calculadora.SymbolsEnum.MULTI_SIGN_CLEAN;
import static com.iconicshield.calculadora.SymbolsEnum.MULTI_SIGN_STRING;
import static com.iconicshield.calculadora.SymbolsEnum.PLUS_SIGN_CLEAN;
import static com.iconicshield.calculadora.SymbolsEnum.PLUS_SIGN_STRING;
import static com.iconicshield.calculadora.SymbolsEnum.POINT_SYMBOL;
import static com.iconicshield.calculadora.SymbolsEnum.POINT_SYMBOL_CLEAN;
import static com.iconicshield.calculadora.SymbolsEnum.SUBSTRACT_SIGN_STRING;

import com.iconicshield.calculadora.SymbolsEnum;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CalculatorService {
    private Double num1, num2;
    private Integer intNum1, intNum2;
    private String sign;
    public static String plusSignString = PLUS_SIGN_STRING.toString();
    public static String plusSignClean = PLUS_SIGN_CLEAN.toString();
    public static String substractSignString = SUBSTRACT_SIGN_STRING.toString();
    public static String multiSignString = MULTI_SIGN_STRING.toString();
    public static String multiSignClean = MULTI_SIGN_CLEAN.toString();
    public static String divSignString = DIV_SIGN_STRING.toString();
    public static String pointSymbolClean = POINT_SYMBOL_CLEAN.toString();
    public static String pointSymbol = POINT_SYMBOL.toString();
    public static final Map<SymbolsEnum, String> SIGNS = Map.of(PLUS_SIGN_STRING, plusSignString,
            SUBSTRACT_SIGN_STRING, substractSignString, MULTI_SIGN_STRING, multiSignString,
            DIV_SIGN_STRING, divSignString, MULTI_SIGN_CLEAN, multiSignClean, PLUS_SIGN_CLEAN, plusSignClean);


    public CalculatorService(){

    }

    public CalculatorService(Double num1, Double num2, String sign){
        this.num1 = num1;
        this.num2 = num2;
        this.sign = sign;
    }

    public CalculatorService(Integer num1, Integer num2, String sign){
        this.intNum1 = num1;
        this.intNum2 = num2;
        this.sign = sign;
    }

    public Double realizeOperationDouble(){
        String keySign = SIGNS.keySet().stream()
                .filter(key -> Objects.equals(SIGNS.get(key), sign))
                .collect(Collectors.toList()).get(0).toString();

        if (keySign.equals(plusSignClean)){
            return num1 + num2;
        } else if (keySign.equals(multiSignClean)) {
            return num1 * num2;
        } else if (keySign.equals(substractSignString)) {
            return num1 - num2;
        }else{
            return num1 / num2;
        }
    }

    public Integer realizeOperationInteger(){
        String keySign = SIGNS.keySet().stream()
                .filter(key -> Objects.equals(SIGNS.get(key), sign))
                .collect(Collectors.toList()).get(0).toString();

        if (keySign.equals(plusSignClean)){
            return intNum1 + intNum2;
        } else if (keySign.equals(multiSignClean)) {
            return intNum1 * intNum2;
        } else if (keySign.equals(substractSignString)) {
            return intNum1 - intNum2;
        }else{
            return intNum1 / intNum2;
        }
    }

    public Double getNum1() {
        return num1;
    }

    public void setNum1(Double num){
        this.num1 = num;
    }

    public Double getNum2(){
        return num2;
    }

    public void setNum2(Double num){
        this.num2 = num;
    }

    public Integer getIntNum1() {
        return intNum1;
    }

    public void setIntNum1(Integer num){
        this.intNum1 = num;
    }

    public Integer getIntNum2(){
        return intNum2;
    }

    public void setIntNum2(Integer num){
        this.intNum2 = num;
    }

    public String getSign(){
        return sign;
    }

    public void setSign(String sign){
        this.sign = sign;
    }
}
