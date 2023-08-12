package cn.srd.library.orm.mybatis.plus.support;

import cn.srd.library.orm.mybatis.plus.config.properties.OrmMybatisPlusProperties;
import cn.srd.library.orm.mybatis.plus.database.postgresql.metadata.handler.PostgresqlTableColumnHandler;
import cn.srd.library.orm.mybatis.plus.database.postgresql.metadata.handler.PostgresqlTableHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Autowired Metadata Bean If {@link OrmMybatisPlusProperties#enableMetadata} Is True
 *
 * @author wjm
 * @since 2023-02-15 09:34:12
 */
@AutoConfiguration(after = OrmMybatisPlusCommonAutoConfiguration.class)
@ConditionalOnEnablePostgresqlMetadata
public class PostgresqlMetadataInjector {

    // /**
    //  * 装配 {@link PostgresqlMetadataMapperScanner}
    //  * TODO wjm 目前若在配置文件里开启了 sugar.orm.mybatis-plus.enableMetadata: true 属性，则必须手工扫描：@MapperScan({"cn.srd.itcp.sugar.orm.mybatis.plus.database.postgresql.metadata.dao"})，目前该种方式会扫描不到 dao，后续需要自动化
    //  *
    //  * @return 装配对象
    //  */
    // @Bean
    // public PostgresqlMetadataMapperScanner postgresqlMetadataMapperScanner() {
    //     return new PostgresqlMetadataMapperScanner();
    //     // return SpringsUtil.registerCapableBean(PostgresqlMetadataMapperScanner.class);
    // }

    /**
     * 装配 {@link PostgresqlTableHandler}
     *
     * @return 装配对象
     */
    @Bean
    public PostgresqlTableHandler postgresqlTableHandler() {
        return new PostgresqlTableHandler();
    }

    /**
     * 装配 {@link PostgresqlTableColumnHandler}
     *
     * @return 装配对象
     */
    @Bean
    public PostgresqlTableColumnHandler postgresqlTableColumnHandler() {
        return new PostgresqlTableColumnHandler();
    }

}
