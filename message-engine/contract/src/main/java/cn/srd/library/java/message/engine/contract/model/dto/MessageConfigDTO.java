// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract.model.dto;

import cn.srd.library.java.message.engine.contract.model.enums.ClientIdGenerateType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author wjm
 * @since 2024-06-01 17:56
 */
@Data
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public abstract class MessageConfigDTO implements Serializable {

    @Serial private static final long serialVersionUID = 8893739317361752384L;

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class BrokerDTO implements Serializable {

        @Serial private static final long serialVersionUID = -5861291771843201177L;

        protected List<String> serverUrls;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public abstract static class ClientDTO implements Serializable {

        @Serial private static final long serialVersionUID = 5434779839371423102L;

        protected String clientId;

        protected String flowId;

        protected ClientIdGenerateType idGenerateType;

        @JsonIgnore
        protected transient Method executeMethod;

    }

}