package br.com.sgfly.model.converter;

import br.com.sgfly.model.enums.CategoriaEnum;
import br.com.sgfly.model.enums.PeriodicidadeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CategoriaConverter implements AttributeConverter<CategoriaEnum, String> {

    @Override
    public String convertToDatabaseColumn(CategoriaEnum attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public CategoriaEnum convertToEntityAttribute(String dbData) {
        return CategoriaEnum.parse(dbData);
    }

}
