package cn.srd.library.java.orm.mybatis.flex.base.id;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.tool.lang.text.Strings;
import com.mybatisflex.core.keygen.IKeyGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerateByUncontrolledStrategy implements IdGenerateStrategy {

    protected static final IdGenerateByUncontrolledStrategy INSTANCE = new IdGenerateByUncontrolledStrategy();

    @Override
    public String getGeneratorName() {
        throw new LibraryJavaInternalException(Strings.format("{}could not build generator name because of the uncontrolled generator!", ModuleView.ORM_MYBATIS_SYSTEM));
    }

    @Override
    public Class<? extends IKeyGenerator> getGenerator() {
        throw new LibraryJavaInternalException(Strings.format("{}could not build generator because of the uncontrolled generator!", ModuleView.ORM_MYBATIS_SYSTEM));
    }

}
