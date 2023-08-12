package cn.srd.library.tool.hack.jdk.core;

import java.lang.annotation.*;

/**
 * 启用导出所有模块
 * <pre>
 *     由于 JDK9、JDK16 的模块化改动导致的相关错误，如：module java.base does not "opens java.lang" to unnamed module 等等；
 *     使用该注解来导出所有模块，参考链接如下：
 *     <a href="https://dev.to/jjbrt/how-to-avoid-resorting-to-add-exports-and-add-opens-in-jdk-16-and-later-j3m">Exporting all modules to all modules at runtime on Java 16 and later</a>
 *     <a href="https://github.com/burningwave/core">BurningWave GitHub</a>
 * </pre>
 *
 * @author wjm
 * @since 2023-02-09 11:19:44
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableExportAllModuleWithJDK16 {

}
