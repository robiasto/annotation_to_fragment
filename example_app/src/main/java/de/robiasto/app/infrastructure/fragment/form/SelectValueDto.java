package de.robiasto.app.infrastructure.fragment.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SelectValueDto {
    private final String name;
    private final String value;
    private final boolean selected;
    private final boolean translatable;
}
