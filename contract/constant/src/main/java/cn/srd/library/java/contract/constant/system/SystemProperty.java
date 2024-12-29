package cn.srd.library.java.contract.constant.system;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * the system property
 *
 * @author wjm
 * @since 2020-01-15 11:02
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SystemProperty {

    /**
     * path to run Java program commands
     */
    public static final String USER_DIR = "user.dir";

}