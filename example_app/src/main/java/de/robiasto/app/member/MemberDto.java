package de.robiasto.app.member;

import de.robiasto.app.team.domain.TeamId;
import de.robiasto.app.user.domain.Position;
import de.robiasto.app.user.domain.UserId;

public record MemberDto(UserId userId, TeamId teamId, Position position, boolean coach) {
    public MemberDto(TeamId teamId, MemberDto memberDto) {
        this(memberDto.userId(), teamId, memberDto.position(), memberDto.coach());
    }
}
