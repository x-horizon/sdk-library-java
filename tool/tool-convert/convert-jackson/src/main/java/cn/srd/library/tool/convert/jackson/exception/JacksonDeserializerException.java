package cn.srd.library.tool.convert.jackson.exception;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * jackson 反序列化异常
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
@StandardException
public class JacksonDeserializerException extends RuntimeException {

    @Serial private static final long serialVersionUID = -2587853132749498768L;

}
