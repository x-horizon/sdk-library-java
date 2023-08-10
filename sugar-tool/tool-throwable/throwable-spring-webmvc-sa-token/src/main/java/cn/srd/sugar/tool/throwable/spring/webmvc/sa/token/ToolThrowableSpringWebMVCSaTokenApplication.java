package cn.srd.sugar.tool.throwable.spring.webmvc.sa.token;

import cn.srd.sugar.tool.spring.common.core.SpringsUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * boot
 */
@Import(SpringsUtil.class)
@SpringBootApplication
public class ToolThrowableSpringWebMVCSaTokenApplication {

    /**
     * main
     *
     * @param args main argument
     */
    public static void main(String[] args) {
        SpringApplication.run(ToolThrowableSpringWebMVCSaTokenApplication.class, args);
    }

}
