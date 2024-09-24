package cn.srd.library.java.concurrent.actor.id;

import cn.srd.library.java.contract.constant.text.SymbolConstant;

import java.io.Serial;
import java.util.Objects;

/**
 * default actor id
 *
 * @author wjm
 * @since 2023-03-20 11:04:19
 */
public abstract class DefaultActorId implements ActorId {

    @Serial private static final long serialVersionUID = 4704603078948188672L;

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        return getId().equals(((DefaultActorId) object).getId());
    }

    @Override
    public String toString() {
        return getActorType().getDescription() + SymbolConstant.VB + getId();
    }

}