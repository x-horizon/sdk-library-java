// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract.model.domain;

import cn.srd.library.java.message.engine.contract.model.enums.ClientIdGenerateType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author wjm
 * @since 2024-06-01 17:56
 */
@Getter
public abstract class MessageConfigDO implements Serializable {

    @Serial private static final long serialVersionUID = 8893739317361752384L;

    @Getter
    public static class BrokerDO implements Serializable {

        @Serial private static final long serialVersionUID = -5861291771843201177L;

        protected List<String> serverUrls;

    }

    @Getter
    public static class ClientDO implements Serializable {

        @Serial private static final long serialVersionUID = 5434779839371423102L;

        protected String clientId;

        protected String flowId;

        protected ClientIdGenerateType idGenerateType;

        @JsonIgnore
        protected transient Method executeMethod;

    }

}