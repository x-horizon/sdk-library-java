package cn.srd.itcp.sugar.component.lock.redisson.webmvc;

import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * boot
 */
@Import(SpringsUtil.class)
@SpringBootApplication
public class ComponentLockRedissonWebMVCApplication {

    /**
     * main
     *
     * @param args main argument
     */
    public static void main(String[] args) {
        SpringApplication.run(ComponentLockRedissonWebMVCApplication.class, args);
    }

}
