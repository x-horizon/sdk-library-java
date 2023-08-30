package com.test.id;

import cn.srd.library.java.contract.constant.core.ModuleView;
import cn.srd.library.java.contract.throwable.core.RunningException;
import cn.srd.library.java.tool.lang.core.StringsUtil;
import com.mybatisflex.core.keygen.IKeyGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdInvalidGenerator implements IKeyGenerator {

    protected static final IdInvalidGenerator INSTANCE = new IdInvalidGenerator();

    @Override
    public Object generate(Object entity, String keyColumn) {
        throw new RunningException(StringsUtil.format("{}could not use invalid id generator to generate id!", ModuleView.ORM_MYBATIS_FLEX_SYSTEM));
    }

}