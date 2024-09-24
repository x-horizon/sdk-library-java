package cn.srd.library.java.concurrent.actor.foo;

import cn.srd.library.java.concurrent.actor.core.ContextAwareActor;
import cn.srd.library.java.tool.convert.api.Converts;

public abstract class TelemetryContextAwareActor<T, R> extends ContextAwareActor<T> {

    // TODO wjm 临时解决通用泛型只能为 map 的问题，这里需要转换多一遍，后续解决

    protected Class<String> getOriginalActorEventDataType() {
        return String.class;
    }

    protected abstract Class<R> getActualActorEventDataType();

    public R convert(T actorEventData) {
        return Converts.onJackson().toBean(getOriginalActorEventDataType().cast(actorEventData), getActualActorEventDataType());
    }

}