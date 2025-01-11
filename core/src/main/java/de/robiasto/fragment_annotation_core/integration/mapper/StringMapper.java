package de.robiasto.fragment_annotation_core.integration.mapper;

import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.utilities.FieldUtility;

import java.lang.reflect.Field;
import java.util.UUID;

/**
 * The StringMapper class provides a method to get the string value of a field.
 * It is used to retrieve the value of a field from a given FieldDtoInterface object.
 * <p>
 * Usage:
 * To use the StringMapper class, create an instance of it and call the `getValue` method, passing a FieldDtoInterface object as a parameter.
 * The `getValue` method will return the string value of the field.
 */
public class StringMapper {

    public String getValue(FieldDtoInterface fieldDto) {
        Object value = FieldUtility.getObject(fieldDto);

        // this.checkValueType(fieldDto.field());

        if (value == null) {
            return "";
        }

        return value.toString();
    }

    private void checkValueType(Field field) {
        if (
                !field.getType().equals(String.class) &&
                        !field.getType().equals(int.class) &&
                        !field.getType().equals(Integer.class) &&
                        !field.getType().equals(Long.class) &&
                        !field.getType().equals(UUID.class)
        ) {
            throw new ValueMapperException("Field " + field.getName() + " invalid type " + field.getType());
        }
    }
}
