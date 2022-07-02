package cn.sugar.redisson;

import cn.sugar.tools.core.SpringsUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SpringsUtil.class)
public class SugarRedissonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SugarRedissonApplication.class, args);
    }

}
