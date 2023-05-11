package br.com.sgfly.model.enums;

import lombok.Getter;

@Getter
public enum PeriodicidadeEnum {

    DIARIA("DIARIA"),
    SEMANAL("SEMANAL"),
    MENSAL("MENSAL"),
    ANUAL("ANUAL");

    private final String value;

    PeriodicidadeEnum(String value){
        this.value = value;
    }

    public static PeriodicidadeEnum parse(String value) {
        if(value == null) return null;

        for (PeriodicidadeEnum e : PeriodicidadeEnum.values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        throw new AssertionError(value);
    }
}
