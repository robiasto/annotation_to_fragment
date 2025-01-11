package de.robiasto.app.team.infrastructure;

import de.robiasto.app.infrastructure.utility.id.AbstractUuid;
import de.robiasto.app.infrastructure.utility.id.RouteIdInterface;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

public class TeamId extends AbstractUuid implements RouteIdInterface {
    @Getter
    public final UUID id;
    public static final String ROUTE_BASE_PATH = "/team";

    public TeamId(@NotNull UUID id) {
       this.id = id;
    }

    public TeamId() {
        this(UUID.randomUUID());
    }

    @Override
    public String getRouteBasePath() {
        return TeamId.ROUTE_BASE_PATH;
    }
}
