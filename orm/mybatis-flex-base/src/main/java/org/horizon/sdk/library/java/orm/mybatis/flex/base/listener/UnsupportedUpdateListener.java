package org.horizon.sdk.library.java.orm.mybatis.flex.base.listener;

import com.mybatisflex.core.FlexGlobalConfig;
import org.horizon.sdk.library.java.contract.model.throwable.UnsupportedException;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

/**
 * the invalid {@link BaseUpdateListener} implement, will not add it to {@link FlexGlobalConfig#getEntityUpdateListeners()}.
 *
 * @author wjm
 * @since 2023-11-13 21:14
 */
public class UnsupportedUpdateListener implements BaseUpdateListener<Void> {

    @Override
    public Class<Void> getEntityType() {
        throw new UnsupportedException(Strings.format("{}unsupported update listener, please check!"));
    }

    @Override
    public void action(Void entity) {
        throw new UnsupportedException(Strings.format("{}unsupported update listener, please check!"));
    }

}