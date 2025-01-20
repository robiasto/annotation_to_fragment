package de.robiasto.app.team.detail.domain;

import de.robiasto.app.infrastructure.fragment.form.ImageTextSelectValueDto;
import de.robiasto.app.infrastructure.fragment.grid.fragments.image_text.ImageTextValueDto;
import de.robiasto.app.infrastructure.utility.id.RouteConfiguration;
import de.robiasto.app.infrastructure.utility.entity_helper.Security;
import de.robiasto.app.team.domain.TeamId;
import de.robiasto.app.user.domain.Position;
import de.robiasto.app.user.domain.UserId;
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
        name = "Team.findAllPlayers",
        query = "SELECT u.id, u.first_name, u.last_name, u.position, tm.team_id, tm.added_date, tm.removed_date " +
                "FROM tt_user u LEFT JOIN member tm ON tm.user_id = u.id AND tm.removed_date IS NULL " +
                "WHERE (tm.team_id = :teamId OR tm.team_id IS NULL) and u.coach = false",
        resultSetMapping = "PlayerEntityMapping"
)
@NamedNativeQuery(
        name = "Team.findAllCoaches",
        query = "SELECT u.id, u.first_name, u.last_name, u.position, tm.team_id, tm.added_date, tm.removed_date " +
                "FROM tt_user u LEFT JOIN member tm ON tm.user_id = u.id AND tm.removed_date IS NULL " +
                "WHERE (tm.team_id = :teamId OR tm.team_id IS NULL) and u.coach = true",
        resultSetMapping = "PlayerEntityMapping"
)

@SqlResultSetMapping(
        name = "PlayerEntityMapping",
        classes = @ConstructorResult(
                targetClass = TeamMemberEntity.class,
                columns = {
                        @ColumnResult(name = "id", type = UUID.class),
                        @ColumnResult(name = "first_name", type = String.class),
                        @ColumnResult(name = "last_name", type = String.class),
                        @ColumnResult(name = "position", type = String.class),
                        @ColumnResult(name = "team_id", type = UUID.class),
                        @ColumnResult(name = "added_date", type = LocalDate.class),
                        @ColumnResult(name = "removed_date", type = LocalDate.class)
                }
        )
)
@NoArgsConstructor
public class TeamMemberEntity {
    @EmbeddedId
    @Getter
    private UserId id;

    private String firstName;

    private String lastName;

    private Position position;

    @Nullable
    @Getter
    private TeamId teamId;

    @Nullable
    private LocalDate addedDate;

    @Nullable
    private LocalDate removedDate;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Constructor for the native query
     */
    public TeamMemberEntity(UUID id, String firstName, String lastName, String position, UUID teamId, LocalDate addedDate, LocalDate removedDate) {
        this.id = new UserId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = Position.valueOf(position);
        this.teamId = teamId != null ? new TeamId(teamId) : null;
        this.addedDate = addedDate;
        this.removedDate = removedDate;
    }

    public ImageTextSelectValueDto toImageTextSelectValueDto(TeamId teamId) {
        boolean selected = this.teamId != null && this.teamId.equals(teamId);
        return new ImageTextSelectValueDto(
                this.getFullName(),
                this.id.toString(),
                RouteConfiguration.getUpdate(this.id),
                RouteConfiguration.getAvatar(this.id),
                this.getTextList(),
                selected,
                Security.isAdmin()
        );
    }

    private List<ImageTextValueDto.LabelTextElement> getTextList() {
        List<ImageTextValueDto.LabelTextElement> textList = new ArrayList<>();

        textList.add(new ImageTextValueDto.LabelTextElement("user.position." + this.position.toString(), "", 0));

        if (this.addedDate != null) {
            textList.add(new ImageTextValueDto.LabelTextElement("listView.start", this.addedDate.toString(), 1));
        }

        if (this.removedDate != null) {
            textList.add(new ImageTextValueDto.LabelTextElement("listView.end", this.removedDate.toString(), 2));
        }

        return textList;
    }
}
