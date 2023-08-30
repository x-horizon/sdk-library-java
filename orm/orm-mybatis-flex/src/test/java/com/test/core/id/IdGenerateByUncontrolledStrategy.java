package com.test.core.id;

import cn.srd.library.java.contract.constant.core.ModuleView;
import cn.srd.library.java.contract.throwable.core.RunningException;
import cn.srd.library.java.tool.lang.core.StringsUtil;
import com.mybatisflex.core.keygen.IKeyGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerateByUncontrolledStrategy implements IdGenerateStrategy {

    protected static final IdGenerateByUncontrolledStrategy INSTANCE = new IdGenerateByUncontrolledStrategy();

    @Override
    public String getGeneratorName() {
        throw new RunningException(StringsUtil.format("{}could not build generator name because of the uncontrolled generator!", ModuleView.ORM_MYBATIS_FLEX_SYSTEM));
    }

    @Override
    public Class<? extends IKeyGenerator> getGenerator() {
        throw new RunningException(StringsUtil.format("{}could not build generator because of the uncontrolled generator!", ModuleView.ORM_MYBATIS_FLEX_SYSTEM));
    }

}
