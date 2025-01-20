package de.robiasto.app.user.detail.domain.team;

import de.robiasto.app.infrastructure.fragment.grid.fragments.image_text.ImageTextValueDto;
import de.robiasto.app.infrastructure.utility.id.RouteConfiguration;
import de.robiasto.app.member.MemberId;
import de.robiasto.app.team.domain.TeamId;
import de.robiasto.app.user.domain.Position;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Immutable
@NamedNativeQuery(
        name = "UserTeamCareer.findAll",
        query = "SELECT m.id, m.position,m.team_id, m.added_date, m.removed_date, m.coach,  t.name "
                + "FROM member m JOIN team t ON t.id = m.team_id "
                + "WHERE (m.user_id = :userId) AND m.removed_date is not null "
                + "order by m.added_date desc",
        resultSetMapping = "UserTeamCareerEntityMapping"
)

@SqlResultSetMapping(
        name = "UserTeamCareerEntityMapping",
        classes = @ConstructorResult(
                targetClass = UserTeamCareerEntity.class,
                columns = {
                        @ColumnResult(name = "id", type = UUID.class),
                        @ColumnResult(name = "position", type = String.class),
                        @ColumnResult(name = "team_id", type = UUID.class),
                        @ColumnResult(name = "added_date", type = LocalDate.class),
                        @ColumnResult(name = "removed_date", type = LocalDate.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "coach", type = boolean.class)
                }
        )
)
@NoArgsConstructor
public class UserTeamCareerEntity {
    /**
     * Constructor for the native query
     */
    public UserTeamCareerEntity(UUID id, String position, UUID teamId, LocalDate addedDate, LocalDate removedDate, String name, boolean coach) {
        this.id = new MemberId(id);
        this.position = Position.valueOf(position);
        this.teamId = new TeamId(teamId);
        this.addedDate = addedDate;
        this.removedDate = removedDate;
        this.name = name;
        this.coach = coach;
    }

    @EmbeddedId
    @Getter
    private MemberId id;

    private Position position;

    private TeamId teamId;

    @Nullable
    private LocalDate addedDate;

    @Nullable
    private LocalDate removedDate;

    private String name;

    private boolean coach;

    public ImageTextValueDto toImageTextDto() {
        return new ImageTextValueDto(
                this.name,
                this.id.toString(),
                RouteConfiguration.getAvatar(this.teamId),
                this.getTextList(),
                true
        );
    }

    private List<ImageTextValueDto.LabelTextElement> getTextList() {
        List<ImageTextValueDto.LabelTextElement> textList = new ArrayList<>();

        textList.add(new ImageTextValueDto.LabelTextElement("user.position." + (this.coach ? "coach" : this.position.toString()), "", 0));

        if (this.addedDate != null) {
            textList.add(new ImageTextValueDto.LabelTextElement("listView.start", this.addedDate.toString(), 1));
        }

        if (this.removedDate != null) {
            textList.add(new ImageTextValueDto.LabelTextElement("listView.end", this.removedDate.toString(), 2));
        }

        return textList;
    }
}
