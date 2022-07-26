package cn.srd.itcp.sugar.mybatis.plus.interceptor;

import cn.srd.itcp.sugar.mybatis.plus.support.SugarMybatisPlusProperties;
import cn.srd.itcp.sugar.tools.core.Objects;
import cn.srd.itcp.sugar.tools.core.SpringsUtil;
import cn.srd.itcp.sugar.tools.core.enums.EnumsUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

public class MybatisPlusPageInterceptor implements MybatisPlusInterceptors {

    public void addInterceptor() {
        DbType dbType = EnumsUtil.capableToEnum(SpringsUtil.getBean(SugarMybatisPlusProperties.class).getDbType(), DbType.class);
        if (Objects.isNotNull(dbType)) {
            // 设置分页插件
            MybatisPlusInnerInterceptorsConfigurer.set(new PaginationInnerInterceptor(dbType));
        }
    }

}
