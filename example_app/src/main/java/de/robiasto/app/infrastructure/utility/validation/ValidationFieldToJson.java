package de.robiasto.app.infrastructure.utility.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.validation.FieldError;

@Getter
class ValidationFieldToJson {
    final boolean valid;

    final String message;

    final String fieldName;

    private ValidationFieldToJson(FieldError error) {
        if (error == null) {
            this.valid = true;
            this.message = "";
            this.fieldName = "";

            return;
        }

        this.valid = false;
        this.message = error.getDefaultMessage();
        this.fieldName = error.getField();
    }

    public static String fromFieldError(FieldError error) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(new ValidationFieldToJson(error));
        } catch (JsonProcessingException e) {
            throw new ValidationException(e.getMessage());
        }
    }
}
