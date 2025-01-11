package de.robiasto.app.infrastructure.fragment.grid;

import lombok.Getter;

@Getter
public enum LinkType {
    LINK("linkType", false),
    FORM("formType", false),
    BUTTON("buttonType", false),
    SUBMIT("submitType", false),
    FORM_CONFIRM("formType", true);

    final String fragmentFunction;
    final boolean confirmation;

    LinkType(String fragmentFunction, boolean confirmation) {
        this.fragmentFunction = fragmentFunction;
        this.confirmation = confirmation;
    }
}
