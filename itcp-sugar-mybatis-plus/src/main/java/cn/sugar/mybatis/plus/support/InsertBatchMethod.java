package cn.sugar.mybatis.plus.support;

import cn.sugar.mybatis.plus.core.MpBaseMapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;

import java.util.Collection;

/**
 * 自定义批量插入方法，这里只重写了方法名，参考 {@link InsertBatchSomeColumn}
 *
 * @author wjm
 * @date 2020/12/25 11:44
 */
public class InsertBatchMethod extends InsertBatchSomeColumn {

    private static final long serialVersionUID = 706400903072847988L;

    /**
     * 对应 {@link MpBaseMapper#insertBatch(Collection)} 的方法名
     *
     * @param sqlMethod
     * @return
     */
    @Override
    public String getMethod(SqlMethod sqlMethod) {
        return "insertBatch";
    }

}