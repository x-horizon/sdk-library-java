// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract.support;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.protocol.MessageModel;
import cn.srd.library.java.message.engine.contract.strategy.MessageEngineType;
import cn.srd.library.java.message.engine.contract.strategy.UniqueClientIdGenerateType;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.id.snowflake.support.SnowflakeIds;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageHandlerSpec;
import org.springframework.messaging.MessageHandler;

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
    public static String getUniqueFlowId(MessageEngineType messageEngineType, Method annotatedMethod) {
        String annotatedMethodDeclaredClassName = annotatedMethod.getDeclaringClass().getName();
        String annotatedMethodName = annotatedMethod.getName();
        String annotatedMethodParameterTypeName = Arrays.stream(annotatedMethod.getParameters()).map(parameter -> STR."\{parameter.getType().getSimpleName()} \{parameter.getName()}").collect(Collectors.joining(", "));
        return STR."\{messageEngineType.getDescription()}-\{annotatedMethodDeclaredClassName}.\{annotatedMethodName}(\{annotatedMethodParameterTypeName})";
    }

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    public static String getUniqueClientId(UniqueClientIdGenerateType generateType, String flowId, String clientId) {
        return Nil.isBlank(clientId) ? STR."\{flowId}-\{generateType.getStrategy().getId()}" : STR."\{clientId}-\{SnowflakeIds.get()}";
    }

    public static MessageHandler getObjectToStringMessageHandler(Object consumerInstance, Method consumerMethod) {
        return message -> Reflects.invoke(consumerInstance, consumerMethod, Converts.withJackson().toBean((String) message.getPayload(), MessageModel.class).requireSuccessAndGetData());
    }

    public static IntegrationFlow getStringToObjectIntegrationFlow(MessageHandler messageHandler) {
        return flow -> flow.transform(messageData -> Converts.withJackson().toString(messageData)).handle(messageHandler);
    }

    public static <H extends MessageHandler> IntegrationFlow getStringToObjectIntegrationFlow(MessageHandlerSpec<?, H> messageHandlerSpec) {
        return flow -> flow.transform(messageData -> Converts.withJackson().toString(messageData)).handle(messageHandlerSpec);
    }

}