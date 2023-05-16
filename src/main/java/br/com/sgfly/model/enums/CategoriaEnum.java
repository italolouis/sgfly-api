package br.com.sgfly.model.enums;

import lombok.Getter;

@Getter
public enum CategoriaEnum {
    CASA("CASA"),
    SAUDE("SAUDE"),
    TRANSPORTE("TRANSPORTE"),
    LAZER("LAZER"),
    ALIMENTACAO("ALIMENTACAO"),
    OUTROS("OUTROS");

    private final String value;

    CategoriaEnum(String value){
        this.value = value;
    }

    public static CategoriaEnum parse(String value) {
        if(value == null) return null;

        for (CategoriaEnum e : CategoriaEnum.values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        throw new AssertionError(value);
    }
}
