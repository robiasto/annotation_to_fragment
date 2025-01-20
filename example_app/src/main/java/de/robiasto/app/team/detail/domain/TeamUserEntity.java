package de.robiasto.app.team.detail.domain;

import de.robiasto.app.member.MemberDto;
import de.robiasto.app.team.domain.TeamId;
import de.robiasto.app.user.domain.Position;
import de.robiasto.app.user.domain.UserId;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.util.UUID;

@Entity
@Immutable
@NamedNativeQuery(
        name = "TeamUserEntity.byUserId",
        query = "SELECT id, position, coach from tt_user where id = :userId",
        resultSetMapping = "TeamUserEntityMapping"
)

@SqlResultSetMapping(
        name = "TeamUserEntityMapping",
        classes = @ConstructorResult(
                targetClass = TeamUserEntity.class,
                columns = {
                        @ColumnResult(name = "id", type = UUID.class),
                        @ColumnResult(name = "position", type = String.class),
                        @ColumnResult(name = "coach", type = boolean.class)
                }
        )
)
@NoArgsConstructor
public class TeamUserEntity {
    /**
     * Constructor for the native query
     */
    public TeamUserEntity(UUID id, String position, boolean coach) {
        this.id = new UserId(id);
        this.position = Position.valueOf(position);
        this.coach = coach;
    }

    @EmbeddedId
    private UserId id;

    private Position position;

    private boolean coach;

    public MemberDto toMemberDto(TeamId teamId) {
        return new MemberDto(id,teamId, position, coach);
    }
}
