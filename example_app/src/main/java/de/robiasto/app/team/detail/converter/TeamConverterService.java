package de.robiasto.app.team.detail.converter;

import de.robiasto.app.team.detail.domain.TeamEntity;
import de.robiasto.app.team.detail.domain.TeamRepository;
import de.robiasto.app.team.domain.TeamId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class TeamConverterService {

    private final TeamRepository repository;

    public TeamEntity getUserById(TeamId teamId) {
        return repository.findById(teamId)
                         .orElseThrow(() -> new TeamNotFoundException(teamId));
    }
}
