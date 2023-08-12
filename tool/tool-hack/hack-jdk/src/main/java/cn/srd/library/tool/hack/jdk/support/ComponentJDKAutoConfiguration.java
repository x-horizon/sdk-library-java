package cn.srd.library.tool.hack.jdk.support;

import cn.srd.library.tool.hack.jdk.core.EnableExportAllModuleWithJDK16;
import cn.srd.library.tool.lang.core.object.Objects;
import cn.srd.library.tool.spring.common.core.SpringsUtil;
import jakarta.annotation.PostConstruct;
import org.burningwave.core.assembler.StaticComponentContainer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Component JDK Hack
 *
 * @author wjm
 * @since 2023-02-09 11:19:44
 */
@AutoConfiguration
public class ComponentJDKAutoConfiguration {

    /**
     * enable export all module with jdk16 if need
     */
    @PostConstruct
    public void enableExportAllModuleWithJDK16IfNeed() {
        if (Objects.isNotEmpty(SpringsUtil.scanPackageByAnnotation(EnableExportAllModuleWithJDK16.class))) {
            StaticComponentContainer.Modules.exportAllToAll();
        }
    }

}
