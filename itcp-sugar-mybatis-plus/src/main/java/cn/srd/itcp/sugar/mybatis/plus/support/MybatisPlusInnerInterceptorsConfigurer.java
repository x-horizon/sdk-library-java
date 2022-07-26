package cn.srd.itcp.sugar.mybatis.plus.support;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * mybatis-plus 插件配置
 *
 * @author wjm
 * @since 2022-07-25 21:51:19
 */
public class MybatisPlusInnerInterceptorsConfigurer {

    /**
     * 存放 mybatis-plus 插件配置的集合
     */
    private static final List<Supplier<InnerInterceptor>> INNER_INTERCEPTOR_SUPPLIERS = new ArrayList<>();

    /**
     * 获取 mybatis-plus 插件配置集合
     *
     * @return mybatis-plus 插件配置集合
     */
    public static List<Supplier<InnerInterceptor>> get() {
        return INNER_INTERCEPTOR_SUPPLIERS;
    }

    /**
     * 设置 mybatis-plus 插件配置
     *
     * @param innerInterceptor mybatis-plus 插件
     */
    public static void set(InnerInterceptor innerInterceptor) {
        INNER_INTERCEPTOR_SUPPLIERS.add(() -> innerInterceptor);
    }

}
