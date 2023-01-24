package cn.srd.itcp.sugar.tools.core;

import cn.hutool.system.SystemUtil;

/**
 * System工具
 *
 * @author wjm
 * @since 2020/1/15 11:02
 */
public class SystemsUtil extends SystemUtil {

    /**
     * private block constructor
     */
    private SystemsUtil() {
    }

    /**
     * 获取项目所在绝对路径，如: /Users/user/Documents/IDE/WorkSpace/project
     *
     * @return 项目所在绝对路径
     */
    public static String getProjectAbsolutePath() {
        return System.getProperty("user.dir");
    }

    /**
     * 获取最大可用的CPU核数
     *
     * @return 最大可用的CPU核数
     */
    public static int getProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }

}
