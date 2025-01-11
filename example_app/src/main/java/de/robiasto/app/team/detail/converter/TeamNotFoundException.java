package de.robiasto.app.team.detail.converter;

import de.robiasto.app.team.infrastructure.TeamId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(TeamId userId) {
        super(String.format("User with id %s not found", userId.id));
    }
}
