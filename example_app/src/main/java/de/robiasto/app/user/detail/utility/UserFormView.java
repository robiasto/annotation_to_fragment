package de.robiasto.app.user.detail.utility;

import de.robiasto.app.infrastructure.fragment.form.FileConfiguration;
import de.robiasto.app.infrastructure.fragment.form.FormField;
import de.robiasto.app.infrastructure.fragment.form.FormFieldFragmentTypes;
import de.robiasto.app.infrastructure.fragment.form.SelectValueDto;
import de.robiasto.app.infrastructure.fragment.grid.FragmentTypes;
import de.robiasto.app.infrastructure.fragment.grid.GridColFragmentConfiguration;
import de.robiasto.app.infrastructure.fragment.grid.HeadlineConfiguration;
import de.robiasto.app.infrastructure.fragment.grid.fragments.headline.HeadlineFragment;
import de.robiasto.app.infrastructure.utility.id.RouteConfiguration;
import de.robiasto.app.user.detail.domain.UserEntity;
import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;
import lombok.Getter;

import java.util.List;

@Getter
public class UserFormView implements AnnotationViewInterface {
    @GridColFragmentConfiguration(
            fragmentType = FragmentTypes.HEADLINE,
            sorting = -1
    )
    @HeadlineConfiguration(HeadlineFragment.Type.H3)
    String teamHeadline = "Team";

    @FormField(
            fragmentType = FormFieldFragmentTypes.FILE,
            sorting = 0,
            isMandatory = false,
            colSpan = FormField.Colspan.HALF
    )
    @FileConfiguration(
            alt = "User Profile"
    )
    protected String avatar;

    @FormField(
            fragmentType = FormFieldFragmentTypes.SELECT_MULTIPLE,
            sorting = 1,
            label = "user.role",
            colSpan = FormField.Colspan.HALF
    )
    List<SelectValueDto> userRole;

    @FormField(
            fragmentType = FormFieldFragmentTypes.SELECT,
            sorting = 2,
            label = "user.gender",
            colSpan = FormField.Colspan.HALF
    )
    List<SelectValueDto> gender;

    @FormField(
            fragmentType = FormFieldFragmentTypes.INPUT_TEXT,
            sorting = 3,
            label = "user.firstName",
            colSpan = FormField.Colspan.HALF
    )
    String firstName = "";

    @FormField(
            fragmentType = FormFieldFragmentTypes.INPUT_TEXT,
            sorting = 4,
            label = "user.lastName",
            colSpan = FormField.Colspan.HALF
    )
    String lastName = "";

    @FormField(
            fragmentType = FormFieldFragmentTypes.INPUT_TEXT,
            sorting = 5,
            label = "user.email",
            colSpan = FormField.Colspan.HALF
    )
    String email = "";

    @FormField(
            fragmentType = FormFieldFragmentTypes.INPUT_TEXT,
            sorting = 6,
            label = "user.birthday"
    )
    String birthday;

    @FormField(
            fragmentType = FormFieldFragmentTypes.INPUT_TEXT,
            sorting = 7,
            label = "user.phoneNumber"
    )
    String phoneNumber;

    @GridColFragmentConfiguration(
            fragmentType = FragmentTypes.HEADLINE,
            sorting = 8
    )
    @HeadlineConfiguration(HeadlineFragment.Type.H3)
    String positionHeadline = "Team position";

    @FormField(
            fragmentType = FormFieldFragmentTypes.CHECKBOX,
            sorting = 9,
            label = "user.coach",
            isMandatory = false,
            colSpan = FormField.Colspan.HALF
    )
    boolean coach;

    @FormField(
            fragmentType = FormFieldFragmentTypes.SELECT,
            sorting = 10,
            label = "user.position",
            isMandatory = false,
            colSpan = FormField.Colspan.HALF
    )
    List<SelectValueDto> position;

    @FormField(
            fragmentType = FormFieldFragmentTypes.SELECT,
            sorting = 11,
            isMandatory = false,
            label = "team.playerSelect"
    )
    List<SelectValueDto> teamPlayerId;

    @FormField(
            fragmentType = FormFieldFragmentTypes.SELECT,
            sorting = 12,
            isMandatory = false,
            label = "team.coachSelect"
    )
    List<SelectValueDto> teamCoachId;

    public UserFormView(UserEntity user, List<SelectValueDto> player, List<SelectValueDto> coach) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.birthday = user.getBirthday().toString();
        this.gender = UserSelectedValueFactory.fromGender(user.getGender());
        this.userRole = UserSelectedValueFactory.fromUserRole(user.getRoles());
        this.coach = user.isCoach();
        this.position = UserSelectedValueFactory.fromPosition(user.getPosition());
        this.avatar = RouteConfiguration.getAvatar(user.getId());
        this.teamPlayerId = player;
        this.teamCoachId = coach;
    }

    public UserFormView(List<SelectValueDto> teamMember, List<SelectValueDto> coach) {
        this.gender = UserSelectedValueFactory.fromGender();
        this.userRole = UserSelectedValueFactory.fromUserRole();
        this.position = UserSelectedValueFactory.fromPosition();
        this.teamPlayerId = teamMember;
        this.teamCoachId = coach;
    }
}
