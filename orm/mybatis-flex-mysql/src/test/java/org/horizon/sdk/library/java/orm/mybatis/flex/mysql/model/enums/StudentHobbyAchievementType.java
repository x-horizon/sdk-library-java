package org.horizon.sdk.library.java.orm.mybatis.flex.mysql.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StudentHobbyAchievementType {

    LEARNING(1, "学术成就"),
    CAREER(2, "职业成就"),
    ART_AND_CULTURE(3, "艺术与文化成就"),
    ;

    private final int code;

    private final String description;

}