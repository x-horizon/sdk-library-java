package cn.srd.itcp.sugar.mybatis.plus.utils;

/**
 * All in one 转换器
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
public class Converts extends cn.srd.itcp.sugar.convert.all.core.Converts {

    protected Converts() {
    }

    /**
     * 应用 支持 MyBatis-Plus 实体的 Mapstruct 转换器
     *
     * @return 支持 MyBatis-Plus 实体的 Mapstruct 转换器
     */
    @Deprecated
    public static MapstructMybatisPlusConverts withMybatisPlusMapstruct() {
        return MapstructMybatisPlusConverts.getInstance();
    }

}

