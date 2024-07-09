// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.api;

import cn.srd.library.java.tool.convert.jackson.JacksonConverts;
import cn.srd.library.java.tool.convert.mapstruct.plus.MapstructConverts;
import cn.srd.library.java.tool.convert.protobuf.ProtobufConverts;
import cn.srd.library.java.tool.convert.spring.SpringConverts;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * all in one converter
 *
 * @author wjm
 * @since 2021-05-01 14:13
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Converts extends cn.srd.library.java.tool.lang.convert.Converts {

    /**
     * apply spring converter
     *
     * @return spring converter
     */
    public static SpringConverts onSpring() {
        return SpringConverts.getInstance();
    }

    /**
     * apply jackson converter
     *
     * @return jackson converter
     */
    public static JacksonConverts onJackson() {
        return JacksonConverts.getInstance();
    }

    /**
     * apply mapstruct converter
     *
     * @return mapstruct converter
     */
    public static MapstructConverts onMapstruct() {
        return MapstructConverts.getInstance();
    }

    /**
     * apply protobuf converter
     *
     * @return protobuf converter
     */
    public static ProtobufConverts onProtobuf() {
        return ProtobufConverts.getInstance();
    }

}