package cn.sugar.hsweb.curd.utils;

/**
 * All in one 转换器
 *
 * @author wjm
 * @date 2022/6/18 19:17
 */
public class Converts extends cn.sugar.convert.all.core.Converts {

    protected Converts() {
    }

    /**
     * 应用 支持 HsWeb 实体的 Mapstruct 转换器
     *
     * @return 支持 HsWeb 实体的 Mapstruct 转换器
     */
    public static MapstructHsWebConverts withHsWebMapstruct() {
        return MapstructHsWebConverts.getInstance();
    }

}

