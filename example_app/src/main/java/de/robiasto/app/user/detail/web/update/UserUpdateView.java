package de.robiasto.app.user.detail.web.update;

import de.robiasto.app.infrastructure.fragment.form.SelectValueDto;
import de.robiasto.app.infrastructure.fragment.grid.FragmentTypes;
import de.robiasto.app.infrastructure.fragment.grid.GridColFragmentConfiguration;
import de.robiasto.app.infrastructure.fragment.grid.HeadlineConfiguration;
import de.robiasto.app.infrastructure.fragment.grid.fragments.headline.HeadlineFragment;
import de.robiasto.app.infrastructure.fragment.grid.fragments.image_text.ImageTextValueDto;
import de.robiasto.app.user.detail.domain.UserEntity;
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
class UserUpdateView implements ExtendedViewInterface {

    @GridColFragmentConfiguration(
            fragmentType = FragmentTypes.HEADLINE,
            sorting = 13
    )
    @HeadlineConfiguration(HeadlineFragment.Type.H3)
    String careerHeadline = "Career";

    @GridColFragmentConfiguration(
            fragmentType = FragmentTypes.IMAGE_TEXT_LIST,
            sorting = 14
    )
    List<ImageTextValueDto> career;

    private List<AnnotationViewInterface> addedViews;

    public UserUpdateView(UserEntity user, List<ImageTextValueDto> imageTextValues, List<SelectValueDto> teams, List<SelectValueDto> coaches) {
        this.addedViews = List.of(new UserFormView(user, teams, coaches));
        this.career = imageTextValues;
    }
}
