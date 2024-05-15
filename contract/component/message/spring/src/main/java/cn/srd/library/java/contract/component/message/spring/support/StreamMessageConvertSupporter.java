package cn.srd.library.java.contract.component.message.spring.support;

import java.util.List;

/**
 * 用于在管道中流动数据的转换支持器
 *
 * @author wjm
 * @since 2023-03-28 19:41
 */
public interface StreamMessageConvertSupporter {

    /**
     * 获取可支持使用某个转换器转换的类
     *
     * @return 可支持使用某个转换器转换的类
     */
    List<Class<?>> getSupportedClasses();

}