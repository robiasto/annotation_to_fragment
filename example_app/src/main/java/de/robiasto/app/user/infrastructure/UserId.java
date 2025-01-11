package de.robiasto.app.user.infrastructure;

import de.robiasto.app.infrastructure.utility.id.AbstractUuid;
import de.robiasto.app.infrastructure.utility.id.RouteIdInterface;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

public class UserId extends AbstractUuid implements RouteIdInterface {
    @Getter
    public final UUID id;
    public static final String ROUTE_BASE_PATH = "/user";

    public UserId(@NotNull UUID id) {
        this.id = id;
    }

    public UserId() {
        this(UUID.randomUUID());
    }

    @Override
    public String getRouteBasePath() {
        return UserId.ROUTE_BASE_PATH;
    }
}
