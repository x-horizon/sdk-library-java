package cn.srd.library.java.web.grpc.client.autoconfigure;

import cn.srd.library.java.contract.constant.spring.SpringInitializeConstant;
import cn.srd.library.java.tool.lang.object.BasePackagePath;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;

/**
 * @author wjm
 * @since 2024-06-14 12:02
 */
@Order(SpringInitializeConstant.HIGHER_INITIALIZE_PRIORITY)
public class GrpcClientBasePackagePathConfigurer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {
        BasePackagePath.register("cn.srd.library.java.web.grpc");
    }

}