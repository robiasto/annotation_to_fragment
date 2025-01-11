package de.robiasto.app.infrastructure.utility.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
