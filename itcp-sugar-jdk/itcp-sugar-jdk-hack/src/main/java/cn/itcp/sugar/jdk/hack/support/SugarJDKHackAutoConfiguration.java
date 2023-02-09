package cn.itcp.sugar.jdk.hack.support;

import cn.itcp.sugar.jdk.hack.core.EnableExportAllModuleWithJDK16;
import cn.srd.itcp.sugar.spring.tool.common.core.SpringsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import jakarta.annotation.PostConstruct;
import org.burningwave.core.assembler.StaticComponentContainer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar JDK Hack
 *
 * @author wjm
 * @since 2022-07-14
 */
public class SugarJDKHackAutoConfiguration {

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
