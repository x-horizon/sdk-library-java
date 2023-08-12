package cn.srd.library.tool.convert.spring.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Spring 转换器
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringConverts {

    /**
     * singleton pattern
     */
    private static final SpringConverts INSTANCE = new SpringConverts();

    /**
     * get singleton instance
     *
     * @return instance
     */
    public static SpringConverts getInstance() {
        return INSTANCE;
    }

}

