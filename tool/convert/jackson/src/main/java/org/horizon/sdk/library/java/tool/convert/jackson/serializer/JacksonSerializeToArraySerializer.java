package org.horizon.sdk.library.java.tool.convert.jackson.serializer;

import com.fasterxml.jackson.core.JsonToken;

/**
 * @author wjm
 * @since 2025-03-21 14:31
 */
public abstract class JacksonSerializeToArraySerializer<T, R> extends JacksonSerializer<T, R> {

    @Override
    public JsonToken getJsonToken() {
        return JsonToken.START_ARRAY;
    }

}