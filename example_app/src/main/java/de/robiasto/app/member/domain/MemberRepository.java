package de.robiasto.app.member.domain;

import de.robiasto.app.member.MemberId;
import de.robiasto.app.team.infrastructure.TeamId;
import de.robiasto.app.user.infrastructure.Position;
import de.robiasto.app.user.infrastructure.UserId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends CrudRepository<MemberEntity, MemberId> {

    List<MemberEntity> findAllByTeamIdAndRemovedDateIsNullAndCoachIsFalse(TeamId teamId);

    Optional<MemberEntity> findByUserIdAndRemovedDateIsNull(UserId userId);

    boolean existsByRemovedDateIsNullAndTeamIdAndUserIdAndCoach(TeamId teamId, UserId userId, boolean coach);
    boolean existsByRemovedDateIsNullAndTeamIdAndUserIdAndPositionAndCoachIsFalse(TeamId teamId, UserId userId, Position position);

    void deleteAllByUserId(UserId userId);
}
