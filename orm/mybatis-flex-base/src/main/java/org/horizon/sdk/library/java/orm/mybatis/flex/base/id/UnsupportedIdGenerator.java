package org.horizon.sdk.library.java.orm.mybatis.flex.base.id;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.contract.model.throwable.UnsupportedException;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

/**
 * the invalid type id generator
 *
 * @author wjm
 * @since 2023-11-12 21:06
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UnsupportedIdGenerator implements IdGenerator {

    @Override
    public Object generate(Object entity, String keyColumn) {
        throw new UnsupportedException(Strings.format("{}unsupported id generator, please check!"));
    }

}