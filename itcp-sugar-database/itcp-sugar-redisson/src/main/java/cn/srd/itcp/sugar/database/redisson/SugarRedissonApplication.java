package cn.srd.itcp.sugar.database.redisson;

import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * boot
 */
@Import(SpringsUtil.class)
@SpringBootApplication
public class SugarRedissonApplication {

    /**
     * main
     *
     * @param args main argument
     */
    public static void main(String[] args) {
        SpringApplication.run(SugarRedissonApplication.class, args);
    }

}
