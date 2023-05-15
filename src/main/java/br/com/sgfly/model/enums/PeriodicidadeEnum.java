package br.com.sgfly.model.enums;

import lombok.Getter;
@Getter
public enum PeriodicidadeEnum {

    DIARIA("DIARIA", "Diária"),
    SEMANAL("SEMANAL", "Semanal"),
    MENSAL("MENSAL", "Mensal"),
    ANUAL("ANUAL", "Anual");

    private final String value;
    private final String description;

    PeriodicidadeEnum(String value,String description){
        this.value = value;
        this.description = description;
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
