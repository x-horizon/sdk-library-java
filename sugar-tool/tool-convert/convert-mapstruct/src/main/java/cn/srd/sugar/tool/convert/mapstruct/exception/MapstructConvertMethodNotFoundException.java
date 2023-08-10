package cn.srd.sugar.tool.convert.mapstruct.exception;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * 未找到转换方法时的异常
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
@StandardException
public class MapstructConvertMethodNotFoundException extends RuntimeException {

    @Serial private static final long serialVersionUID = -6760724713927618055L;

}
