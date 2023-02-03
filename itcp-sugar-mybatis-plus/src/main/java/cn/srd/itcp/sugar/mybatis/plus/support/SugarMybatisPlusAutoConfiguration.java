package cn.srd.itcp.sugar.mybatis.plus.support;

import cn.srd.itcp.sugar.mybatis.plus.database.mysql.utils.MysqlBinaryPrimaryKeyGenerator;
import cn.srd.itcp.sugar.mybatis.plus.database.postgresql.metadata.handler.PostgresqlTableColumnHandler;
import cn.srd.itcp.sugar.mybatis.plus.database.postgresql.metadata.handler.PostgresqlTableHandler;
import cn.srd.itcp.sugar.mybatis.plus.interceptor.MybatisPlusInnerInterceptorsConfigurer;
import cn.srd.itcp.sugar.mybatis.plus.interceptor.MybatisPlusInterceptors;
import cn.srd.itcp.sugar.mybatis.plus.interceptor.MybatisPlusPageInterceptor;
import cn.srd.itcp.sugar.spring.common.tool.core.SpringsUtil;
import cn.srd.itcp.sugar.tools.core.EnumsUtil;
import cn.srd.itcp.sugar.tools.core.Objects;
import cn.srd.itcp.sugar.tools.core.ReflectsUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Mybatis Plus
 *
 * @author wjm
 * @since 2022-07-05
 */
@Configuration
@ConditionalOnClass(MybatisPlusAutoConfiguration.class)
public class SugarMybatisPlusAutoConfiguration {

    /**
     * 装配 {@link MybatisPlusInterceptor}
     *
     * @return 装配对象
     */
    @Bean
    @DependsOn("mybatisPlusPageInterceptors")
    // TODO wjm 这里可能还有问题，后续需要详细测试与优化
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        SpringsUtil.getBeansOfType(MybatisPlusInterceptors.class).forEach((beanName, beanClass) -> ReflectsUtil.invoke(beanClass, "addInterceptor"));
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        MybatisPlusInnerInterceptorsConfigurer.get().forEach(innerInterceptorSupplier -> mybatisPlusInterceptor.addInnerInterceptor(innerInterceptorSupplier.get()));
        return mybatisPlusInterceptor;
    }

    /**
     * 装配 {@link ConfigurationCustomizer}
     *
     * @return 装配对象
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        // 开启 XML resultMap 下划线自动转换驼峰，不用写 <result> 相关的 数据库字段名 与 实体类字段名 的转换
        return configuration -> configuration.setMapUnderscoreToCamelCase(true);
    }

    /**
     * 装配 {@link MybatisPlusPageInterceptor}
     *
     * @return 装配对象
     */
    @Bean
    @DependsOn("sugarMybatisPlusProperties")
    public MybatisPlusPageInterceptor mybatisPlusPageInterceptors() {
        return new MybatisPlusPageInterceptor();
    }

    /**
     * 装配 {@link MysqlBinaryPrimaryKeyGenerator}
     *
     * @return 装配对象
     */
    @Bean
    @DependsOn("sugarMybatisPlusProperties")
    public MysqlBinaryPrimaryKeyGenerator mysqlBinUuidPrimaryKeyGenerator() {
        if (Objects.equals(DbType.MYSQL, EnumsUtil.capableToEnum(SpringsUtil.getBean(SugarMybatisPlusProperties.class).getDbType(), DbType.class))) {
            return new MysqlBinaryPrimaryKeyGenerator();
        }
        return null;
    }

    /**
     * 装配 {@link PostgresqlTableColumnHandler}
     * TODO wjm 目前若在配置文件里开启了 sugar-mybatis-plus-enable 属性，则必须手工扫描：@MapperScan({"cn.srd.itcp.sugar.mybatis.plus.database.postgresql.metadata.dao"})，后续需要自动化
     *
     * @return 装配对象
     */
    @Bean
    @DependsOn("sugarMybatisPlusProperties")
    public PostgresqlTableColumnHandler postgresqlTableColumnHandler() {
        if (Objects.equals(DbType.POSTGRE_SQL, EnumsUtil.capableToEnum(SpringsUtil.getBean(SugarMybatisPlusProperties.class).getDbType(), DbType.class))) {
            return new PostgresqlTableColumnHandler();
        }
        return null;
    }

    /**
     * 装配 {@link PostgresqlTableHandler}
     *
     * @return 装配对象
     */
    @Bean
    @DependsOn("sugarMybatisPlusProperties")
    public PostgresqlTableHandler postgresqlTableHandler() {
        if (Objects.equals(DbType.POSTGRE_SQL, EnumsUtil.capableToEnum(SpringsUtil.getBean(SugarMybatisPlusProperties.class).getDbType(), DbType.class))) {
            return new PostgresqlTableHandler();
        }
        return null;
    }

    /**
     * 装配 {@link SugarMybatisPlusProperties}
     *
     * @return 装配对象
     */
    @Bean
    public SugarMybatisPlusProperties sugarMybatisPlusProperties() {
        return SpringsUtil.registerCapableBean(SugarMybatisPlusProperties.class);
    }

}
