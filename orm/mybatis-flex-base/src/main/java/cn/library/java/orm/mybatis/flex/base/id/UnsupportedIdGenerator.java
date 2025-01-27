package cn.library.java.orm.mybatis.flex.base.id;

import cn.library.java.contract.model.throwable.UnsupportedException;
import cn.library.java.tool.lang.text.Strings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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