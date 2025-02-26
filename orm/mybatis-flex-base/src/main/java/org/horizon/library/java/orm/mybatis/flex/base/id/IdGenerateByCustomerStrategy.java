package org.horizon.library.java.orm.mybatis.flex.base.id;

import org.horizon.library.java.contract.constant.module.ModuleView;
import org.horizon.library.java.contract.model.throwable.LibraryJavaInternalException;
import org.horizon.library.java.tool.lang.functional.Assert;
import org.horizon.library.java.tool.lang.reflect.Reflects;
import com.mybatisflex.core.keygen.KeyGeneratorFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * the customer type id generate strategy
 *
 * @author wjm
 * @see IdConfig
 * @since 2023-11-12 21:06
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IdGenerateByCustomerStrategy implements IdGenerateStrategy {

    @Override
    public void validateIdConfig(IdConfig idConfig) {
        warningIfNotDefaultIdGenerateSQL(idConfig.generateSQL());
        Assert.of().setMessage("{}id generator config - current id generate strategy is [{}] but no generator specified in [{}]!", ModuleView.ORM_MYBATIS_SYSTEM, this.getClass().getName(), IdConfig.class.getName())
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfEquals(UnsupportedIdGenerator.class, idConfig.generator());
    }

    @Override
    public void registerIdGenerator(IdConfig idConfig) {
        KeyGeneratorFactory.register(getGeneratorName(), Reflects.newInstance(idConfig.generator()));
    }

}