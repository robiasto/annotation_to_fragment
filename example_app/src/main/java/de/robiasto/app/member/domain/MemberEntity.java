package de.robiasto.app.member.domain;

import de.robiasto.app.member.MemberId;
import de.robiasto.app.team.domain.TeamId;
import de.robiasto.app.user.domain.Position;
import de.robiasto.app.user.domain.UserId;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "member")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity {
    @EmbeddedId
    private MemberId id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id"))
    private UserId userId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "team_id"))
    private TeamId teamId;

    @NotNull
    private LocalDate addedDate;

    @Nullable
    private LocalDate removedDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Position position;

    private boolean coach;

    public MemberEntity(
            TeamId teamId,
            UserId userId,
            Position position,
            boolean coach
    ){
        this.id = new MemberId();
        this.userId = userId;
        this.teamId = teamId;
        this.addedDate = LocalDate.now();
        this.position = position;
        this.coach = coach;
    }

    public void setRemoved(){
        this.removedDate = LocalDate.now();
    }
}
