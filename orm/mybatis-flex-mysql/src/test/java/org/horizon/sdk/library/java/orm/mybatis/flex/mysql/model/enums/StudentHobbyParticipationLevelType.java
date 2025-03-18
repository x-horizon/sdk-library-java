package org.horizon.sdk.library.java.orm.mybatis.flex.mysql.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StudentHobbyParticipationLevelType {

    BEGINNER(1, "初学者"),
    PROFESSIONAL(2, "专业级"),
    MASTER(3, "大师级"),
    ;

    private final int code;

    private final String description;

}