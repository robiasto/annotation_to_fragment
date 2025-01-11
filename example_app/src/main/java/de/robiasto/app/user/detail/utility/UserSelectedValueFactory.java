package de.robiasto.app.user.detail.utility;

import de.robiasto.app.infrastructure.fragment.form.SelectValueDto;
import de.robiasto.app.user.domain.Gender;
import de.robiasto.app.user.infrastructure.Position;
import de.robiasto.app.user.infrastructure.UserRole;

import java.util.Arrays;
import java.util.List;

public class UserSelectedValueFactory {
    private UserSelectedValueFactory() {}

    public static List<SelectValueDto> fromGender(Gender role) {
        return Arrays.stream(Gender.values()).map(
                gender -> new SelectValueDto(
                        "user.gender." +gender.name(),
                        gender.name(),
                        role.name().equals(gender.name()),
                        true
                )
        ).toList();
    }

    public static List<SelectValueDto> fromGender() {
        return Arrays.stream(Gender.values()).map(
                gender -> new SelectValueDto(
                        "user.gender." +gender.name(),
                        gender.name(),
                        false,
                        true
                )
        ).toList();
    }

    public static List<SelectValueDto> fromUserRole(List<UserRole> selectedRoles) {
        return Arrays.stream(UserRole.values()).map(
                userRole -> new SelectValueDto(
                        "user.security." + userRole.name(),
                        userRole.name(),
                        selectedRoles.contains(userRole),
                        true
                )
        ).toList();
    }

    public static List<SelectValueDto> fromUserRole() {
        return Arrays.stream(UserRole.values()).map(
                userRole -> new SelectValueDto(
                        "user.security." +userRole.name(),
                        userRole.name(),
                        false,
                        true
                )
        ).toList();
    }

    public static List<SelectValueDto> fromPosition(Position position) {
        return Arrays.stream(Position.values()).map(
                positionEntry -> new SelectValueDto(
                        "user.position." +positionEntry.name(),
                        positionEntry.name(),
                        position.name().equals(positionEntry.name()),
                        true
                )
        ).toList();
    }

    public static List<SelectValueDto> fromPosition() {
        return Arrays.stream(Position.values()).map(
                positionEntry -> new SelectValueDto(
                        "user.position." +positionEntry.name(),
                        positionEntry.name(),
                        false,
                        true
                )
        ).toList();
    }
}
