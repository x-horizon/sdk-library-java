// package cn.srd.library.java.studio.low.code.autoconfigure;
//
// import cn.srd.library.java.contract.constant.spring.SpringInitializeConstant;
// import cn.srd.library.java.tool.lang.object.BasePackagePath;
// import org.springframework.context.ApplicationContextInitializer;
// import org.springframework.context.ConfigurableApplicationContext;
// import org.springframework.core.annotation.Order;
// import org.springframework.lang.NonNull;
// import org.springframework.stereotype.Component;
//
// @Component
// @Order(SpringInitializeConstant.HIGHER_INITIALIZE_PRIORITY)
// public class BasePackagePathConfigurer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//
//     @Override
//     public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {
//         BasePackagePath.register("cn.srd.library.java");
//     }
//
// }