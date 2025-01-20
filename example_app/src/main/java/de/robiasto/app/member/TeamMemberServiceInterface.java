package de.robiasto.app.member;

import de.robiasto.app.team.domain.TeamId;
import de.robiasto.app.user.domain.UserId;

import java.util.List;

public interface TeamMemberServiceInterface {
    void updateTeam(TeamId teamEntity, List<MemberDto> userEntities);
    void updatePlayer(MemberDto memberDto);
    void updateCoach(MemberDto memberDto);
    void deleteAllByUserId(UserId userId);
}
