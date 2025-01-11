package de.robiasto.app.team.detail.service;

import de.robiasto.app.infrastructure.fragment.form.FileConfiguration;
import de.robiasto.app.infrastructure.fragment.form.FormField;
import de.robiasto.app.infrastructure.fragment.form.FormFieldFragmentTypes;
import de.robiasto.app.infrastructure.fragment.form.ImageTextSelectValueDto;
import de.robiasto.app.infrastructure.utility.id.RouteConfiguration;
import de.robiasto.app.team.detail.domain.TeamEntity;
import de.robiasto.app.team.domain.TeamMemberEntity;
import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;
import lombok.Getter;

import java.util.List;

@Getter
public class TeamFormView implements AnnotationViewInterface {
    @FormField(
            fragmentType = FormFieldFragmentTypes.FILE,
            sorting = 0,
            isMandatory = false
    )
    @FileConfiguration(alt = "Team logo")
    String avatar = "";

    @FormField(
            fragmentType = FormFieldFragmentTypes.INPUT_TEXT,
            sorting = 1,
            label = "team.name"
    )
    String name = "";

    @FormField(
            fragmentType = FormFieldFragmentTypes.SELECT_IMAGE_TEXT,
            sorting = 4,
            label = "team.coach",
            isMandatory = false
    )
    List<ImageTextSelectValueDto> coach;

    @FormField(
            fragmentType = FormFieldFragmentTypes.SELECT_MULTIPLE_IMAGE_TEXT,
            sorting = 5,
            label = "team.player"
    )
    List<ImageTextSelectValueDto> players;


    public TeamFormView(TeamEntity team, List<TeamMemberEntity> coaches, List<TeamMemberEntity> players) {
        this.name = team.getName();
        this.coach = coaches.stream()
                            .map(
                                    coachEntity -> coachEntity.toImageTextSelectValueDto(team.getId()))
                            .toList();
        this.players = players.stream()
                            .map(
                                    player -> player.toImageTextSelectValueDto(team.getId()))
                            .toList();

        this.avatar = RouteConfiguration.getAvatar(team.getId());
    }

    public TeamFormView(List<TeamMemberEntity> coaches, List<TeamMemberEntity> players) {
        this.coach = coaches.stream()
                            .map(
                                    coachEntity -> coachEntity.toImageTextSelectValueDto(null))
                            .toList();
        this.players = players.stream()
                            .map(
                                    player -> player.toImageTextSelectValueDto(null))
                            .toList();
    }
}
