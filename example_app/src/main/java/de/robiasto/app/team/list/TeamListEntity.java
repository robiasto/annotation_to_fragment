package de.robiasto.app.team.list;

import de.robiasto.app.team.domain.TeamId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "team")
@NamedQuery(name = "ListTeamEntity.findAll", query = "SELECT u FROM TeamListEntity u")
@Getter
class TeamListEntity {

    @EmbeddedId
    private TeamId id;

    private String name;
}
