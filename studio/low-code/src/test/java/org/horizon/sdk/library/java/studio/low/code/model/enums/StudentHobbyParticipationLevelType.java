package org.horizon.sdk.library.java.studio.low.code.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 学生兴趣爱好参与程度类型
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
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