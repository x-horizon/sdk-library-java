package org.horizon.sdk.library.java.registration.autoconfigure;

import com.alibaba.cloud.nacos.NacosConfigAutoConfiguration;
import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.PropertyKeyConst;
import jakarta.annotation.PostConstruct;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.reflect.Reflects;
import org.horizon.sdk.library.java.tool.spring.contract.support.Springs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;

/**
 * @author wjm
 * @since 2025-04-13 17:35
 */
@AutoConfigureAfter({NacosConfigProperties.class, NacosConfigAutoConfiguration.class})
public class NacosAutoConfigurer {

    @Value("${spring.cloud.nacos.config.namespace:}")
    private String nacosConfigNamespace;

    @Value("${spring.cloud.nacos.config.group:}")
    private String nacosConfigGroup;

    @Value("${spring.cloud.nacos.config.file-extension:}")
    private String nacosConfigFileExtension;

    @Autowired private NacosConfigManager nacosConfigManager;

    @PostConstruct
    public void init() {
        NacosConfigProperties nacosConfigProperties = Springs.getBean(NacosConfigProperties.class);
        if (Nil.isNotBlank(nacosConfigNamespace)) {
            nacosConfigProperties.setNamespace(nacosConfigNamespace);
            Reflects.setFieldValue(nacosConfigManager.getConfigService(), PropertyKeyConst.NAMESPACE, nacosConfigNamespace);
        }
        if (Nil.isNotBlank(nacosConfigGroup)) {
            nacosConfigProperties.setGroup(nacosConfigGroup);
        }
        if (Nil.isNotBlank(nacosConfigFileExtension)) {
            nacosConfigProperties.setFileExtension(nacosConfigFileExtension);
        }
    }

}