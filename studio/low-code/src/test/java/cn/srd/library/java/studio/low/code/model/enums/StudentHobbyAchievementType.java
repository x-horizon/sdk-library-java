// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 学生兴趣爱好获得成就类型
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
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