package org.horizon.library.java.oss.contract.model.property;

import org.horizon.library.java.contract.component.oss.model.enums.OssType;
import org.horizon.library.java.tool.lang.collection.Collections;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author wjm
 * @since 2024-06-19 23:51
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "library-java.oss")
public class OssProperty {

    private List<Config> configs = Collections.newArrayList();

    @Getter
    @Setter
    public static class Config {

        private OssType type;

        private String serverUrl;

        private String accessKey;

        private String secretKey;

    }

}