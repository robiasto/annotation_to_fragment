package de.robiasto.app.infrastructure.utility.id;

import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
public abstract class AbstractUuid implements Serializable {
    public abstract UUID getId();

    @Override
    public boolean equals(Object o) {
        return o instanceof AbstractUuid abstractUuid && getId().equals(abstractUuid.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public String toString() {
        return getId().toString();
    }
}
