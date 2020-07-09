package fr.isen.m1.schedule.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PositionConverter implements AttributeConverter<Double, String> {

    @Override
    public String convertToDatabaseColumn(Double aDouble) {
        String string = Double.toString(aDouble);
        System.out.println(string);
        return string;
    }




    @Override
    public Double convertToEntityAttribute(String value) {
        return Double.valueOf(value);
    }
}