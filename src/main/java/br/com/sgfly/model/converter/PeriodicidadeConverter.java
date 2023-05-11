package br.com.sgfly.model.converter;

import br.com.sgfly.model.enums.PeriodicidadeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PeriodicidadeConverter implements AttributeConverter<PeriodicidadeEnum, String> {

    @Override
    public String convertToDatabaseColumn(PeriodicidadeEnum attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public PeriodicidadeEnum convertToEntityAttribute(String dbData) {
        return PeriodicidadeEnum.parse(dbData);
    }

}
