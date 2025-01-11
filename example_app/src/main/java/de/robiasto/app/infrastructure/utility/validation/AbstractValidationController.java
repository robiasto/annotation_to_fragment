package de.robiasto.app.infrastructure.utility.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public abstract class AbstractValidationController<R> {
    public static final String ROUTE_VALIDATE = "/validate";

    @PostMapping(
            value = ROUTE_VALIDATE + "/{fieldName}", produces = "application/json"
    )
    public ResponseEntity<String> validateCreateField(
            @PathVariable("fieldName") String fieldName,
            @Validated() R formData,
            BindingResult bindingResult
    ) {
        return ResponseEntity.ok(ValidationFieldToJson.fromFieldError(bindingResult.getFieldError(fieldName)));
    }

    @PostMapping(
            value = ROUTE_VALIDATE, produces = "application/json"
    )
    public ResponseEntity<List<String>> validateCreateFields(
            @Validated() R formData,
            BindingResult bindingResult
    ) {

        return ResponseEntity.ok(
                bindingResult.getFieldErrors().stream().map(ValidationFieldToJson::fromFieldError).toList()
        );
    }
}
