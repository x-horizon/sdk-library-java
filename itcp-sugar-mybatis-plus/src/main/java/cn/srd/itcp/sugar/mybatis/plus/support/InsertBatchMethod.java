package cn.srd.itcp.sugar.mybatis.plus.support;

import cn.srd.itcp.sugar.mybatis.plus.core.GenericCurdDao;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;

import java.util.Collection;

/**
 * 自定义批量插入方法，这里只重写了方法名，参考 {@link InsertBatchSomeColumn}
 *
 * @author wjm
 * @since 2020/12/25 11:44
 */
public class InsertBatchMethod extends InsertBatchSomeColumn {

    private static final long serialVersionUID = 706400903072847988L;

    /**
     * 对应 {@link GenericCurdDao#insertBatch(Collection)} 的方法名
     *
     * @param sqlMethod
     * @return
     */
    @Override
    public String getMethod(SqlMethod sqlMethod) {
        return "insertBatch";
    }

}