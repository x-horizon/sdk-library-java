package org.horizon.sdk.library.java.orm.mybatis.flex.base.id;

import org.horizon.sdk.library.java.tool.id.uuid.support.UUIDs;
import com.mybatisflex.core.keygen.KeyGeneratorFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * the uuid type id generate strategy
 *
 * @author wjm
 * @see IdConfig
 * @since 2023-11-12 21:06
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IdGenerateByUUIDStrategy implements IdGenerateStrategy {

    @Override
    public void registerIdGenerator(IdConfig idConfig) {
        KeyGeneratorFactory.register(getGeneratorName(), (IdGenerator) (entity, keyColumn) -> UUIDs.fastGetString());
    }

}