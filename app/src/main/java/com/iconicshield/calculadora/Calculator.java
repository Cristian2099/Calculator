package com.iconicshield.calculadora;

import static com.iconicshield.calculadora.SymbolsEnum.*;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Calculator {
    private Double num1, num2;
    private String sign;
    static String plusSignString = PLUS_SIGN_STRING.toString();
    static final String plusSignClean = PLUS_SIGN_CLEAN.toString();
    static String substractSignString = SUBSTRACT_SIGN_STRING.toString();
    static String multiSignString = MULTI_SIGN_STRING.toString();
    static String multiSignClean = MULTI_SIGN_CLEAN.toString();
    static String divSignString = DIV_SIGN_STRING.toString();
    static String pointSymbolClean = POINT_SYMBOL_CLEAN.toString();
    static String pointSymbol = POINT_SYMBOL.toString();
    public static final Map<SymbolsEnum, String> SIGNS = Map.of(PLUS_SIGN_STRING, plusSignString,
            SUBSTRACT_SIGN_STRING, substractSignString, MULTI_SIGN_STRING, multiSignString,
            DIV_SIGN_STRING, divSignString, MULTI_SIGN_CLEAN, multiSignClean, PLUS_SIGN_CLEAN, plusSignClean);


    public Calculator(){

    }

    public Calculator(Double num1, Double num2, String sign){
        this.num1 = num1;
        this.num2 = num2;
        this.sign = sign;
    }

    public Double realizeOperation(){
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

    public String getSign(){
        return sign;
    }

    public void setSign(String sign){
        this.sign = sign;
    }
}
