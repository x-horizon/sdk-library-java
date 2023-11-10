package cn.srd.library.java.orm.mybatis.flex.base.id;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.tool.lang.text.Strings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdInvalidGenerator implements IdGenerator {

    private static final class SingleTonHolder {

        private static final IdInvalidGenerator INSTANCE = new IdInvalidGenerator();

    }

    public static IdInvalidGenerator getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    @Override
    public Object generate(Object entity, String keyColumn) {
        throw new LibraryJavaInternalException(Strings.format("{}could not use invalid id generator to generate id!", ModuleView.ORM_MYBATIS_SYSTEM));
    }

}