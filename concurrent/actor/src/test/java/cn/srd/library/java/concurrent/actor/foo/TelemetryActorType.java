package cn.srd.library.java.concurrent.actor.foo;

import cn.srd.library.java.concurrent.actor.core.ActorType;
import cn.srd.library.java.tool.enums.EnumAutowired;
import cn.srd.library.java.tool.enums.strategy.EnumAutowiredFieldMatchByContainIgnoreCaseRule;
import lombok.Getter;

@Getter
@EnumAutowired(rootClasses = TelemetryActorTypeStrategy.class, allowNull = true, matchRule = EnumAutowiredFieldMatchByContainIgnoreCaseRule.class)
public enum TelemetryActorType implements ActorType {

    ROOT(1, "TelemetryRootActor"),
    METRIC(2, "TelemetryMetricActor"),
    TRAFFIC_REALTIME(3, "TelemetryTrafficRealtimeActor"),
    GREEN_WAVE_LIFE_CYCLE_ROUTE(4, "TelemetryGreenWaveLifeCycleRouteActor"),
    GREEN_WAVE_LIFE_CYCLE_SECTION(5, "TelemetryGreenWaveLifeCycleSectionActor"),
    GREEN_WAVE_CONTROL(6, "TelemetryGreenWaveControlActor"),

    ;

    TelemetryActorType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    private final int code;

    private final String description;

    private TelemetryActorTypeStrategy strategy;

}