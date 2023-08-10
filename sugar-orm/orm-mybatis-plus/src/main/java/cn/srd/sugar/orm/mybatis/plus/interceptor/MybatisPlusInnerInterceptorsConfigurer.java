package cn.srd.sugar.orm.mybatis.plus.interceptor;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * mybatis-plus 拦截器配置
 *
 * @author wjm
 * @since 2022-07-25 21:51:19
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MybatisPlusInnerInterceptorsConfigurer {

    /**
     * 存放 mybatis-plus 拦截器的集合
     */
    private static final List<Supplier<InnerInterceptor>> INNER_INTERCEPTOR_SUPPLIERS = new ArrayList<>();

    /**
     * 获取 mybatis-plus 拦截器集合
     *
     * @return mybatis-plus 拦截器集合
     */
    public static List<Supplier<InnerInterceptor>> get() {
        return INNER_INTERCEPTOR_SUPPLIERS;
    }

    /**
     * 设置 mybatis-plus 拦截器
     *
     * @param innerInterceptor mybatis-plus 拦截器
     */
    public static void set(InnerInterceptor innerInterceptor) {
        INNER_INTERCEPTOR_SUPPLIERS.add(() -> innerInterceptor);
    }

}
