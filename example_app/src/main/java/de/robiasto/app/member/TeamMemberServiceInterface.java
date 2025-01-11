package de.robiasto.app.member;

import de.robiasto.app.team.infrastructure.TeamId;
import de.robiasto.app.user.infrastructure.UserId;

import java.util.List;

public interface TeamMemberServiceInterface {
    void updateTeam(TeamId teamEntity, List<MemberDto> userEntities);
    void updatePlayer(MemberDto memberDto);
    void updateCoach(MemberDto memberDto);
    void deleteAllByUserId(UserId userId);
}
