package de.robiasto.app.infrastructure.fragment.form.fragment;

public class MissingFormFieldConfigurationException extends RuntimeException {
    public MissingFormFieldConfigurationException(String message) {
        super(message);
    }
}
