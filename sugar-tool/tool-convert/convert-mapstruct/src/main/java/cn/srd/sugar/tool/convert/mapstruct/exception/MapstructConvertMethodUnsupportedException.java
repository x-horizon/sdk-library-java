package cn.srd.sugar.tool.convert.mapstruct.exception;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * 不支持的转换方法异常
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
@StandardException
public class MapstructConvertMethodUnsupportedException extends RuntimeException {

    @Serial private static final long serialVersionUID = 2781116473610565298L;

}
