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
import org.springframework.core.annotation.Order;

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
    @Order()
    @DependsOn("sugarMybatisPlusProperties")
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        DbType dbType = EnumsUtil.capableToEnum(SpringsUtil.getBean(SugarMybatisPlusProperties.class).getDbType(), DbType.class);
        if (Objects.isNotNull(dbType)) {
            // 设置分页插件
            MybatisPlusInnerInterceptorsConfigurer.set(new PaginationInnerInterceptor(dbType));
        }
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        MybatisPlusInnerInterceptorsConfigurer.get().forEach(innerInterceptorSupplier -> interceptor.addInnerInterceptor(innerInterceptorSupplier.get()));
        return interceptor;
    }

    @Bean
    @DependsOn("sugarMybatisPlusProperties")
    public MySQLBinaryPrimaryKeyGenerator mysqlBinUuidPrimaryKeyGenerator() {
        if (Objects.equals(DbType.MYSQL, EnumsUtil.capableToEnum(SpringsUtil.getBean(SugarMybatisPlusProperties.class).getDbType(), DbType.class))) {
            return new MySQLBinaryPrimaryKeyGenerator();
        }
        return null;
    }

    @Bean
    @DependsOn("sugarMybatisPlusProperties")
    public PostgresqlTableColumnHandler postgresqlTableColumnHandler() {
        if (Objects.equals(DbType.POSTGRE_SQL, EnumsUtil.capableToEnum(SpringsUtil.getBean(SugarMybatisPlusProperties.class).getDbType(), DbType.class))) {
            return new PostgresqlTableColumnHandler();
        }
        return null;
    }

    @Bean
    @DependsOn("sugarMybatisPlusProperties")
    public PostgresqlTableHandler postgresqlTableHandler() {
        if (Objects.equals(DbType.POSTGRE_SQL, EnumsUtil.capableToEnum(SpringsUtil.getBean(SugarMybatisPlusProperties.class).getDbType(), DbType.class))) {
            return new PostgresqlTableHandler();
        }
        return null;

    }

    @Bean
    public SugarMybatisPlusProperties sugarMybatisPlusProperties() {
        return SpringsUtil.registerCapableBean(SugarMybatisPlusProperties.class);
    }
    
}
