package de.robiasto.app.user.detail.domain.team;

import de.robiasto.app.infrastructure.fragment.form.SelectValueDto;
import de.robiasto.app.team.domain.TeamId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.util.UUID;

@Entity
@Immutable
@NamedNativeQuery(
        name = "UserTeam.findAllPlayerWithId",
        query = "SELECT t.id, t.name, CASE WHEN m.id IS NOT NULL THEN true ELSE false END AS selected "
                + "FROM team t LEFT JOIN member m ON m.team_id = t.id and m.removed_date is null and m.user_id = :userId and m.coach = false",
        resultSetMapping = "UserTeamEntityMapping"
)

@NamedNativeQuery(
        name = "UserTeam.findAllPlayer",
        query = "SELECT t.id, t.name, false as selected "
                + "FROM team t",
        resultSetMapping = "UserTeamEntityMapping"
)

@NamedNativeQuery(
        name = "UserTeam.findAllCoachesWithId",
        query = "SELECT t.id, t.name" +
                ", (Select m3.user_id from member m3 where m3.team_id = t.id and m3.removed_date is null and m3.coach is true AND m3.user_id = :userId) is Not null as selected " +
                "FROM team t " +
                "JOIN member m ON m.team_id = t.id AND m.removed_date IS NULL " +
                "GROUP BY t.id " +
                "HAVING COUNT(CASE WHEN m.coach = true THEN 1 END) = 0 " +
                "    OR EXISTS (SELECT m2.user_id FROM member m2 WHERE m2.removed_date isnull and t.id = m2.team_id AND m2.coach is true AND m2.user_id = :userId);",
        resultSetMapping = "UserTeamEntityMapping"
)

@NamedNativeQuery(
        name = "UserTeam.findAllCoaches",
        query = "SELECT t.id, t.name, false as selected " +
                "FROM team t " +
                "         left JOIN member m ON m.team_id = t.id AND m.removed_date IS NULL " +
                "GROUP BY t.id, t.name " +
                "HAVING COUNT(CASE WHEN m.coach = true THEN 1 END) = 0 ",
        resultSetMapping = "UserTeamEntityMapping"
)



@SqlResultSetMapping(
        name = "UserTeamEntityMapping",
        classes = @ConstructorResult(
                targetClass = UserTeamEntity.class,
                columns = {
                        @ColumnResult(name = "id", type = UUID.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "selected", type = boolean.class)
                }
        )
)


@NoArgsConstructor
public class UserTeamEntity {
    /**
     * Constructor for the native query
     */
    public UserTeamEntity(UUID id, String name, boolean selected) {
        this.id = new TeamId(id);
        this.name = name;
        this.selected = selected;

    }

    @EmbeddedId
    @Getter
    private TeamId id;

    private String name;

    private boolean selected;

    public SelectValueDto toSelectValueDto() {
        return new SelectValueDto(name, id.getId().toString(), selected, false);
    }
}
