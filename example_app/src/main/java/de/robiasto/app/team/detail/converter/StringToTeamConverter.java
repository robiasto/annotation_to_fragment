package de.robiasto.app.team.detail.converter;


import de.robiasto.app.team.detail.domain.TeamEntity;
import de.robiasto.app.team.domain.TeamId;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
@Profile("!mvcTest")
class StringToTeamConverter implements Converter<String, TeamEntity> {

    private final TeamConverterService repository;

    @Override
    public TeamEntity convert(@NonNull String s) {
        TeamId teamId = new TeamId(UUID.fromString(s));

        return repository.getUserById(teamId);
    }
}
