package cn.srd.library.orm.mybatis.plus.interceptor;

import cn.srd.library.orm.mybatis.plus.config.properties.OrmMybatisPlusProperties;
import cn.srd.library.tool.lang.core.EnumsUtil;
import cn.srd.library.tool.lang.core.object.Objects;
import cn.srd.library.tool.spring.common.core.SpringsUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * mybatis-plus 拦截器 - 分页插件
 *
 * @author wjm
 * @since 2022-07-25 21:51:19
 */
@Order(Ordered.LOWEST_PRECEDENCE - 1)
public class MybatisPlusPageInterceptor implements MybatisPlusInterceptors {

    public void addInterceptor() {
        DbType dbType = EnumsUtil.capableToEnum(SpringsUtil.getBean(OrmMybatisPlusProperties.class).getDatabase(), DbType.class);
        if (Objects.isNotNull(dbType)) {
            // 设置分页插件
            MybatisPlusInnerInterceptorsConfigurer.set(new PaginationInnerInterceptor(dbType));
        }
    }

}
