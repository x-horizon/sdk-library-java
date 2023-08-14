package cn.srd.library.java.tool.lang.core;

import cn.hutool.system.SystemUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * System工具
 *
 * @author wjm
 * @since 2020/1/15 11:02
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SystemsUtil extends SystemUtil {

    /**
     * 获取项目所在绝对路径，如: /Users/user/Documents/IDE/WorkSpace/project
     *
     * @return 项目所在绝对路径
     */
    public static String getProjectAbsolutePath() {
        return System.getProperty("user.dir");
    }

    /**
     * 获取可用的CPU核数
     *
     * @return 可用的CPU核数
     */
    public static int getAvailableProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }

}
