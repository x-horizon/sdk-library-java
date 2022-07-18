package cn.srd.itcp.sugar.mybatis.plus.support;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Mybatis Plus
 *
 * @author wjm
 * @date 2022-07-05
 */
@Configuration
@ConditionalOnClass(MybatisPlusAutoConfiguration.class)
public class SugarMybatisPlusAutoConfiguration {

    // TODO wjm 需要增加条件注入，当连接的数据库为 mysql 时才注入，或者使用 @Enable注入
    @Bean
    public MySQLBinaryPrimaryKeyGenerator mysqlBinUuidPrimaryKeyGenerator() {
        return new MySQLBinaryPrimaryKeyGenerator();
    }

    // TODO wjm 暂时去除，后续根据最新版本的 mybatis-plus 进行自定义
    // /**
    //  * mybatis-plus 插件配置
    //  *
    //  * @return
    //  */
    // @Bean
    // public MybatisPlusInterceptor mybatisPlusInterceptor() {
    //     MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    //     // 分页插件，一级缓存和二级缓存遵循 mybatis 的规则
    //     interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
    //     return interceptor;
    // }
    // /**
    //  * 分页插件配置：设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题
    //  *
    //  * @return
    //  */
    // @Bean
    // public ConfigurationCustomizer configurationCustomizer() {
    //     return configuration -> configuration.setUseDeprecatedExecutor(false);
    // }

    // @Bean
    // public MapperScannerConfigurer mapperScannerConfigurer() {
    //     MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
    //     // 可以通过环境变量获取你的mapper路径,这样mapper扫描可以通过配置文件配置了
    //     scannerConfigurer.setBasePackage("cn.srd.itcp.portal.dao");
    //     return scannerConfigurer;
    // }

    // @Primary
    // @Bean("db1SqlSessionFactory")
    // public SqlSessionFactory db1SqlSessionFactory(DataSource dataSource) throws Exception {
    //     /**
    //      * 使用 mybatis plus 配置
    //      */
    //     MybatisSqlSessionFactoryBean b1 = new MybatisSqlSessionFactoryBean();
    //     System.out.println("dataSourceLyz" + dataSource.toString());
    //     b1.setDataSource(dataSource);
    //     b1.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:cn/srd/itcp/portal/dao/impl/*Mapper.xml"));
    //     return b1.getObject();
    // }

}
