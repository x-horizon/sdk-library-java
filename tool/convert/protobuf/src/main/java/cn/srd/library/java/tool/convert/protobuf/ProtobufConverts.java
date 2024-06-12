// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.protobuf;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import com.google.protobuf.Message;
import com.google.protobuf.TextFormat;
import com.google.protobuf.util.JsonFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

/**
 * @author wjm
 * @since 2024-06-12 16:08
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProtobufConverts {

    private static final ProtobufConverts INSTANCE = new ProtobufConverts();

    public static ProtobufConverts getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public String toString(Message source) {
        return TextFormat.printer().printToString(source);
    }

    @SneakyThrows
    public String toJsonString(Message source) {
        return JsonFormat.printer().print(source);
    }

    @SneakyThrows
    public <B extends Message.Builder> B toBuilder(String source, B targetBuilder) {
        JsonFormat.parser().ignoringUnknownFields().merge(source, targetBuilder);
        return targetBuilder;
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <M extends Message, B extends Message.Builder> M toBean(String source, B targetBuilder) {
        return (M) toBuilder(source, targetBuilder).build();
    }

}