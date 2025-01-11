package de.robiasto.app.member;

import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.UUID;


public record MemberId(UUID id) implements Serializable {

    public MemberId {
        Assert.notNull(id, "id must not be null");
    }

    public MemberId() {
        this(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return this.id.toString();
    }
}
