package cn.srd.itcp.sugar.tool.core.extractor;

import cn.srd.itcp.sugar.tool.core.ArraysUtil;
import cn.srd.itcp.sugar.tool.core.ClassesUtil;
import cn.srd.itcp.sugar.tool.core.ReflectsUtil;
import cn.srd.itcp.sugar.tool.core.object.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法形参列表提取器
 * <pre>
 * 支持以下功能：
 * 1、提取方法形参列表中某个形参的值作为返回结果；
 * 2、提取方法形参列表中某个形参内的某个字段值作为返回结果；
 * </pre>
 *
 * @param <T> 提取结果类型
 * @author wjm
 * @see FunctionParameterExtractor
 * @since 2022-07-30 22:29:14
 */
public interface FunctionParameterExtractorHandler<T> {

    /**
     * 表示提取方法形参列表中的第一个形参时的索引
     */
    int EXTRACT_FIRST_PARAMETER_INDEX = 1;

    /**
     * 表示提取方法形参列表中的第一个形参时的索引
     */
    int[] EXTRACT_FIRST_PARAMETER_INDEXES = new int[]{0, EXTRACT_FIRST_PARAMETER_INDEX};

    /**
     * 是否为提取方法形参列表中的第一个形参
     *
     * @param extractIndex 提取方法形参列表中的第几个形参
     * @return 是否为提取方法形参列表中的第一个形参
     */
    default boolean isExtractFirstParameter(int extractIndex) {
        return ArraysUtil.contains(EXTRACT_FIRST_PARAMETER_INDEXES, extractIndex);
    }

    /**
     * 确保从方法形参列表提取到返回结果
     *
     * @param methodParameters 方法形参列表
     * @param extractIndex     需要提取方法形参列表中的第几个形参
     * @param extractFieldName 当需要提取方法形参列表中某个形参内的某个字段值作为返回结果时该字段的字段名，若为空则表示直接提取方法形参列表中某个形参的值作为返回结果
     * @return 返回结果
     */
    @SuppressWarnings("unchecked")
    default T ensureExtractInFunctionParameter(Object[] methodParameters, int extractIndex, String extractFieldName) {
        Objects.requireFalse(extractIndex < 0);
        Objects.requireNotEmpty(methodParameters);
        // 获取需要提取方法中的第几个形参的索引
        int internalExtractIndex = isExtractFirstParameter(extractIndex) ? EXTRACT_FIRST_PARAMETER_INDEX : extractIndex;
        // 根据 internalExtractIndex 获取方法形参列表上的第几个形参，internalExtractIndex 为 0 时获取第 1 个，为 n 时获取第 n 个；
        Object methodParameter = internalExtractIndex > 0 ? methodParameters[internalExtractIndex - 1] : methodParameters[0];
        // 提取最终结果
        if (Objects.isBlank(extractFieldName)) {
            return (T) methodParameter;
        }
        if (ClassesUtil.isAssignable(Iterable.class, methodParameter.getClass())) {
            List<Object> extractResult = new ArrayList<>();
            ((Iterable<?>) methodParameter).forEach(element -> extractResult.add(ReflectsUtil.getFieldValue(element, extractFieldName)));
            return (T) extractResult;
        }
        return (T) ReflectsUtil.getFieldValue(methodParameter, extractFieldName);
    }

}
