package cn.srd.itcp.sugar.convert.mapstruct.core;

import java.lang.annotation.*;

/**
 * 用于标记哪些是 Mapstruct 类
 *
 * @author wjm
 * @date 2021/5/1 14:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface BindMapstruct {

}
