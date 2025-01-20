package de.robiasto.app.team.detail.service;

import de.robiasto.app.infrastructure.utility.entity_helper.EntityQueryUtilityInterface;
import de.robiasto.app.infrastructure.utility.entity_helper.EntityQueryAttribute;
import de.robiasto.app.member.MemberDto;
import de.robiasto.app.member.TeamMemberServiceInterface;
import de.robiasto.app.team.detail.domain.TeamEntity;
import de.robiasto.app.team.detail.domain.TeamUserEntity;
import de.robiasto.app.team.detail.domain.TeamMemberEntity;
import de.robiasto.app.team.domain.TeamId;
import de.robiasto.app.user.domain.UserId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TeamMemberService {

    private EntityQueryUtilityInterface entityManager;

    private final TeamMemberServiceInterface memberService;

    public List<TeamMemberEntity> findAllTeamPlayer(TeamId team) {
        return this.entityManager.executeQuery(
                "Team.findAllPlayers",
                TeamMemberEntity.class,
                new EntityQueryAttribute("teamId", team.id)
        );
    }

    public List<TeamMemberEntity> findAllTeamCoach(TeamId team) {
        return this.entityManager.executeQuery(
                "Team.findAllCoaches",
                TeamMemberEntity.class,
                new EntityQueryAttribute("teamId", team.id)
        );
    }

    public void updateTeamMember(TeamEntity team, TeamResponse teamResponse) {
        if (teamResponse.getCoach() != null) {
            memberService.updateCoach(this.findByTeamIdAndUserId(team.getId(), teamResponse.getCoach()));
        }

        memberService.updateTeam(
                team.getId(),
                teamResponse.getPlayers().stream().map(
                        userId -> this.findByTeamIdAndUserId(team.getId(), userId)
                ).toList()
        );
    }

    private MemberDto findByTeamIdAndUserId(TeamId teamId, UserId userId) {
        return entityManager.executeQuery(
                                    "TeamUserEntity.byUserId",
                                    TeamUserEntity.class,
                                    new EntityQueryAttribute("userId", userId.id)
                            ).stream()
                            .findFirst()
                            .orElseThrow()
                            .toMemberDto(teamId);
    }
}
