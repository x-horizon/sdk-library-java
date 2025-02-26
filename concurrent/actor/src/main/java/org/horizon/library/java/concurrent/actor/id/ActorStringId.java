package org.horizon.library.java.concurrent.actor.id;

import lombok.Getter;

import java.util.Objects;

/**
 * @author wjm
 * @since 2025-01-26 22:39
 */
public class ActorStringId implements ActorId {

    @Getter
    private final String id;

    public ActorStringId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public boolean equals(Object compared) {
        if (this == compared) {
            return true;
        }
        if (compared == null || getClass() != compared.getClass()) {
            return false;
        }
        return this.id.equals(((ActorStringId) compared).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}