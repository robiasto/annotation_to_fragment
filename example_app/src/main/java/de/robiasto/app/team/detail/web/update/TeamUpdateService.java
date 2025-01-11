package de.robiasto.app.team.detail.web.update;

import de.robiasto.app.infrastructure.utility.file.FileUtility;
import de.robiasto.app.infrastructure.utility.id.RouteConfiguration;
import de.robiasto.app.team.detail.domain.TeamEntity;
import de.robiasto.app.team.detail.domain.TeamRepository;
import de.robiasto.app.team.detail.service.TeamMemberService;
import de.robiasto.app.team.detail.service.TeamResponse;
import de.robiasto.app.team.domain.TeamMemberEntity;
import de.robiasto.app.team.infrastructure.TeamId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
class TeamUpdateService {

    private final TeamRepository teamRepository;

    private final TeamMemberService teamMemberService;

    private final FileUtility fileUtility;

    public void update(TeamEntity team, TeamResponse teamResponse) {
        team.setName(teamResponse.getName());
        this.teamRepository.save(team);

        fileUtility.saveFile(teamResponse.getAvatar(), RouteConfiguration.getAvatar(team.getId()));
    }

    public List<TeamMemberEntity> getCoaches(TeamId teamId) {
        return this.teamMemberService.findAllTeamCoach(teamId);
    }

    public List<TeamMemberEntity> getPlayers(TeamId teamId) {
        return this.teamMemberService.findAllTeamPlayer(teamId);
    }
}
