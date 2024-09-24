package cn.srd.library.java.concurrent.actor.foo;

import cn.srd.library.java.concurrent.actor.event.ActorEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class TelemetryActorEvent<T> implements ActorEvent<T> {

    @Serial private static final long serialVersionUID = 7528780201493288290L;

    private T data;

    private List<TelemetryActorType> bindActorTypes;

}