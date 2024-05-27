// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.constant.text.SymbolConstant;
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

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    public static String getId(Method annotatedMethod) {
        String annotatedMethodDeclaredClassName = annotatedMethod.getDeclaringClass().getSimpleName();
        String annotatedMethodName = annotatedMethod.getName();
        String annotatedMethodParameterTypeName = Arrays.stream(annotatedMethod.getParameterTypes()).map(Class::getSimpleName).collect(Collectors.joining(SymbolConstant.UNDERLINE));
        return STR."\{annotatedMethodDeclaredClassName}_\{annotatedMethodName}_\{annotatedMethodParameterTypeName}";
    }

}