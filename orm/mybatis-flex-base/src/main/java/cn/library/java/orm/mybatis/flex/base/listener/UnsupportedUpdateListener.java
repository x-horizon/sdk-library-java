package cn.library.java.orm.mybatis.flex.base.listener;

import cn.library.java.contract.model.throwable.UnsupportedException;
import cn.library.java.tool.lang.text.Strings;
import com.mybatisflex.core.FlexGlobalConfig;

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