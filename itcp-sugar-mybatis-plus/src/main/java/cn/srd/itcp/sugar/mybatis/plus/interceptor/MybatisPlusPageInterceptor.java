package cn.srd.itcp.sugar.mybatis.plus.interceptor;

import cn.srd.itcp.sugar.mybatis.plus.support.SugarMybatisPlusProperties;
import cn.srd.itcp.sugar.spring.common.tool.core.SpringsUtil;
import cn.srd.itcp.sugar.tool.core.EnumsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

/**
 * mybatis-plus 拦截器 - 分页插件
 *
 * @author wjm
 * @since 2022-07-25 21:51:19
 */
public class MybatisPlusPageInterceptor implements MybatisPlusInterceptors {

    public void addInterceptor() {
        DbType dbType = EnumsUtil.capableToEnum(SpringsUtil.getBean(SugarMybatisPlusProperties.class).getDbType(), DbType.class);
        if (Objects.isNotNull(dbType)) {
            // 设置分页插件
            MybatisPlusInnerInterceptorsConfigurer.set(new PaginationInnerInterceptor(dbType));
        }
    }

}
