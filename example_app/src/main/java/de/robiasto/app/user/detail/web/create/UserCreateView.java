package de.robiasto.app.user.detail.web.create;

import de.robiasto.app.infrastructure.fragment.form.FormField;
import de.robiasto.app.infrastructure.fragment.form.FormFieldFragmentTypes;
import de.robiasto.app.infrastructure.fragment.form.SelectValueDto;
import de.robiasto.app.user.detail.utility.UserFormView;
import de.robiasto.app.user.detail.utility.validation.password.PasswordsMatch;
import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;
import de.robiasto.fragment_annotation_core.interfaces.ExtendedViewInterface;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@PasswordsMatch
class UserCreateView implements ExtendedViewInterface {
    @FormField(
            fragmentType = FormFieldFragmentTypes.PASSWORD,
            sorting = 14,
            label = "user.password"
    )
    private String password;

    @FormField(
            fragmentType = FormFieldFragmentTypes.PASSWORD,
            sorting = 15,
            label = "user.password.repeated"
    )
    private String passwordRepeated;

    private List<AnnotationViewInterface> addedViews;

    public UserCreateView(List<SelectValueDto> teams, List<SelectValueDto> coaches) {
        this.addedViews = List.of(new UserFormView(teams, coaches));
     }

}
