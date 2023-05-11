package br.com.sgfly.model.converter;

import br.com.sgfly.model.enums.StatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StatusConverter implements AttributeConverter<StatusEnum, String> {

    @Override
    public String convertToDatabaseColumn(StatusEnum attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public StatusEnum convertToEntityAttribute(String dbData) {
        return StatusEnum.parse(dbData);
    }

}
