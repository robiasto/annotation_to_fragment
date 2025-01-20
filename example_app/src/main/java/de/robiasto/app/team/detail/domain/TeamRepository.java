package de.robiasto.app.team.detail.domain;

import de.robiasto.app.team.domain.TeamId;
import org.springframework.data.repository.CrudRepository;


public interface TeamRepository extends CrudRepository<TeamEntity, TeamId> { }
