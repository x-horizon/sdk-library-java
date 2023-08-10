package cn.srd.sugar.tool.convert.jackson.exception;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * jackson 序列化异常
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
@StandardException
public class JacksonSerializerException extends RuntimeException {

    @Serial private static final long serialVersionUID = -5404014469580075860L;

}
