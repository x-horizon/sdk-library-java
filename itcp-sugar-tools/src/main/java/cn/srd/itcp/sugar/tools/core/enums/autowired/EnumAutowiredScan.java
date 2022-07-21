package cn.srd.itcp.sugar.tools.core.enums.autowired;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.annotation.*;

/**
 * 指定在哪些包下扫描标记了 {@link EnumAutowired} 的类，若不指定，默认在 {@link SpringBootApplication} 所在的包路径下扫描
 *
 * @author wjm
 * @date 2022-07-20 11:37:23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EnumAutowiredScan {

    /**
     * 指定在哪些包下扫描标记了 {@link EnumAutowired} 的类，若不指定，默认在 {@link SpringBootApplication} 所在的包路径下扫描
     */
    String[] value();

}
