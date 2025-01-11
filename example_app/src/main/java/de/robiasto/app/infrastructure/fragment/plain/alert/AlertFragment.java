package de.robiasto.app.infrastructure.fragment.plain.alert;

import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;

public class AlertFragment extends AbstractLocalFragment {
    public final String text;

    public final boolean isTranslation;

    public final AlertFragment.Type type;

    AlertFragment(String text, boolean isTranslation, Type type) {
        this.text = text;
        this.isTranslation = isTranslation;
        this.type = type;
    }

    public static AlertFragment withTranslation(String text, Type type) {
        return new AlertFragment(text, true, type);
    }

    public static AlertFragment withText(String text, Type type) {
        return new AlertFragment(text, false, type);
    }

    public enum Type {
        SUCCESS("success"),
        ERROR("error");

        public final String status;

        Type(String status) {
            this.status = status;
        }
    }
}
