package cn.srd.library.tool.throwable.spring.webmvc.redisson;

import cn.srd.library.tool.spring.common.core.SpringsUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * boot
 */
@Import(SpringsUtil.class)
@SpringBootApplication
public class ToolThrowableSpringWebMVCRedissonApplication {

    /**
     * main
     *
     * @param args main argument
     */
    public static void main(String[] args) {
        SpringApplication.run(ToolThrowableSpringWebMVCRedissonApplication.class, args);
    }

}
