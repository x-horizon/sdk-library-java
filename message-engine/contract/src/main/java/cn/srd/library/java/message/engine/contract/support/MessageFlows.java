// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract.support;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.tool.id.snowflake.SnowflakeIds;
import cn.srd.library.java.tool.lang.object.Nil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author wjm
 * @since 2024-05-27 18:11
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageFlows {

    public static final long DEFAULT_COMPLETION_TIMEOUT = 30000L;

    public static final long DISCONNECT_COMPLETION_TIMEOUT = 5000L;

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    public static String getUniqueFlowId(Method annotatedMethod) {
        String annotatedMethodDeclaredClassName = annotatedMethod.getDeclaringClass().getName();
        String annotatedMethodName = annotatedMethod.getName();
        String annotatedMethodParameterTypeName = Arrays.stream(annotatedMethod.getParameters()).map(parameter -> STR."\{parameter.getType().getSimpleName()} \{parameter.getName()}").collect(Collectors.joining(", "));
        return STR."\{annotatedMethodDeclaredClassName}.\{annotatedMethodName}(\{annotatedMethodParameterTypeName})";
    }

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    public static String getUniqueClientId(String flowId, String clientId) {
        return Nil.isBlank(clientId) ? STR."\{flowId}-\{SnowflakeIds.get()}" : STR."\{clientId}-\{SnowflakeIds.get()}";
    }

}