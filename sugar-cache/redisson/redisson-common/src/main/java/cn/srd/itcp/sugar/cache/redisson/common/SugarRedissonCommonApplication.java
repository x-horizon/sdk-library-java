package cn.srd.itcp.sugar.cache.redisson.common;

import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * boot
 */
@Import(SpringsUtil.class)
@SpringBootApplication
public class SugarRedissonCommonApplication {

    /**
     * main
     *
     * @param args main argument
     */
    public static void main(String[] args) {
        SpringApplication.run(SugarRedissonCommonApplication.class, args);
    }

}
