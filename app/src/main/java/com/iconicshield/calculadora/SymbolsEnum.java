package com.iconicshield.calculadora;

import androidx.annotation.NonNull;

public enum SymbolsEnum {
    PLUS_SIGN_STRING("+"),
    SUBSTRACT_SIGN_STRING("-"),
    DIV_SIGN_STRING("/"),
    PLUS_SIGN_CLEAN("\\+"),
    MULTI_SIGN_STRING("*"),
    MULTI_SIGN_CLEAN("\\*"),
    POINT_SYMBOL("."),
    POINT_SYMBOL_CLEAN("\\.");

    private final String value;
    SymbolsEnum(String value) {
        this.value = value;
    }

    @NonNull
    @Override
    public String toString() {
        return value;
    }
}
