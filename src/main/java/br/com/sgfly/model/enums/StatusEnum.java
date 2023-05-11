package br.com.sgfly.model.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {
    ATIVO("A"),
    INATIVO("I");

    private final String value;

    StatusEnum(String value){
        this.value = value;
    }

    public static StatusEnum parse(String value) {
        if(value == null) return null;

        for (StatusEnum e : StatusEnum.values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        throw new AssertionError(value);
    }
}
