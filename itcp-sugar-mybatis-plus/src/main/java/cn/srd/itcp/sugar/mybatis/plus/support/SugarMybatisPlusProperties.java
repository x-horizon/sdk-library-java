package cn.srd.itcp.sugar.mybatis.plus.support;

import com.baomidou.mybatisplus.annotation.DbType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties for Sugar Mybatis Plus
 *
 * @author wjm
 * @date 2022-07-18 17:59:54
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "sugar.mybatis-plus")
public class SugarMybatisPlusProperties {

    /**
     * 数据库类型，可用值参考：{@link DbType} TODO wjm 后续实现直接注入枚举
     */
    private String dbType;

}
