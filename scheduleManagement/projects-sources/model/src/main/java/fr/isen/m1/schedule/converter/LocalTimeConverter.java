package Algorithm.Schedule.converter;

import java.sql.Time;
import java.time.LocalTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter to persist LocalDate and LocalDateTime with JPA 2.1 and Hibernate
 * older than 5.0 version
 **/

@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {

    @Override
    public Time convertToDatabaseColumn(LocalTime localTime) {
        return (localTime == null ? null : Time.valueOf(localTime));
    }

    @Override
    public LocalTime convertToEntityAttribute(Time time) {
        return (time == null ? null : time.toLocalTime());
    }

}