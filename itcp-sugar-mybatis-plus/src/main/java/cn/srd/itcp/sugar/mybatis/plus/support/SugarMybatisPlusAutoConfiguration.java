package cn.srd.itcp.sugar.mybatis.plus.support;

import cn.srd.itcp.sugar.mybatis.plus.metadata.handler.PostgresqlTableColumnHandler;
import cn.srd.itcp.sugar.mybatis.plus.metadata.handler.PostgresqlTableHandler;
import cn.srd.itcp.sugar.tools.core.Objects;
import cn.srd.itcp.sugar.tools.core.SpringsUtil;
import cn.srd.itcp.sugar.tools.core.enums.EnumsUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Mybatis Plus
 *
 * @author wjm
 * @date 2022-07-05
 */
@Configuration
@ConditionalOnClass(MybatisPlusAutoConfiguration.class)
public class SugarMybatisPlusAutoConfiguration {

    @Bean
    @DependsOn("sugarMybatisPlusProperties")
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        DbType dbType = EnumsUtil.capableToEnum(SpringsUtil.getBean(SugarMybatisPlusProperties.class).getDbType(), DbType.class);
        if (Objects.isNotNull(dbType)) {
            MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
            // 分页插件，一级缓存和二级缓存遵循 mybatis 的规则
            interceptor.addInnerInterceptor(new PaginationInnerInterceptor(dbType));
            return interceptor;
        }
        return null;
    }

    @Bean
    public SugarMybatisPlusProperties sugarMybatisPlusProperties() {
        return SpringsUtil.registerCapableBean(SugarMybatisPlusProperties.class);
    }

    @Bean
    public MySQLBinaryPrimaryKeyGenerator mysqlBinUuidPrimaryKeyGenerator() {
        if (Objects.isNotNull(EnumsUtil.capableToEnum(SpringsUtil.getBean(SugarMybatisPlusProperties.class).getDbType(), DbType.class))) {
            return new MySQLBinaryPrimaryKeyGenerator();
        }
        return null;
    }

    @Bean
    public PostgresqlTableColumnHandler postgresqlTableColumnHandler() {
        if (Objects.isNotNull(EnumsUtil.capableToEnum(SpringsUtil.getBean(SugarMybatisPlusProperties.class).getDbType(), DbType.class))) {
            return new PostgresqlTableColumnHandler();
        }
        return null;
    }

    @Bean
    public PostgresqlTableHandler postgresqlTableHandler() {
        if (Objects.isNotNull(EnumsUtil.capableToEnum(SpringsUtil.getBean(SugarMybatisPlusProperties.class).getDbType(), DbType.class))) {
            return new PostgresqlTableHandler();
        }
        return null;

    }

}
