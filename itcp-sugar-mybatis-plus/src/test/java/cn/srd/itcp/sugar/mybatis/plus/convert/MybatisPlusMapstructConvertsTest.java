package cn.srd.itcp.sugar.mybatis.plus.convert;

import cn.srd.itcp.sugar.convert.mapstruct.exception.MapstructConvertMethodNotFoundException;
import cn.srd.itcp.sugar.mybatis.plus.convert.bean.domain.StudentDO;
import cn.srd.itcp.sugar.mybatis.plus.convert.bean.vo.StudentVO;
import cn.srd.itcp.sugar.mybatis.plus.utils.Converts;
import cn.srd.itcp.sugar.tools.core.CollectionsUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusMapstructConvertsTest {

    private static final StudentVO DEFAULT_STUDENT_VO = StudentVO.newVO();
    private static final StudentDO DEFAULT_STUDENT_DO = StudentDO.newDO();
    private static final List<StudentDO> DEFAULT_STUDENT_DOS = StudentDO.newDOs();
    private static final Page<StudentDO> DEFAULT_PAGE = new Page<>();

    static {
        DEFAULT_PAGE.setPages(1);
        DEFAULT_PAGE.setCurrent(1);
        DEFAULT_PAGE.setRecords(DEFAULT_STUDENT_DOS);
        DEFAULT_PAGE.setSize(3);
        DEFAULT_PAGE.setTotal(3);
    }

    @Test
    public void testMybatisPlusMapstructBeanConverter() {
        // 转换成功
        Assert.assertNotNull(Converts.withMybatisPlusMapstruct().toPageBean(DEFAULT_PAGE, StudentVO.class).getData().get(0));
        // 转换带默认值，源对象为 null 的情况下，将默认值包装为 PageResult<默认值> 并返回
        Assert.assertNotNull(Converts.withMybatisPlusMapstruct().toPageBean(null, StudentVO.class, DEFAULT_STUDENT_VO).getData().get(0));
        // 转换失败（未匹配到转换方法），未开启静默转换，应抛出异常
        Assert.assertThrows(MapstructConvertMethodNotFoundException.class, () -> Converts.withMybatisPlusMapstruct().toPageBean(DEFAULT_PAGE, StudentDO.class));
        // 转换失败（未匹配到转换方法），开启了静默转换，且没有设置默认值，应打印异常并返回空的 PageResult，注意观察日志是否正确
        Assert.assertEquals(0, Converts.withMybatisPlusMapstruct().toPageBean(DEFAULT_PAGE, StudentDO.class, true).getData().size());
        // 转换失败（未匹配到转换方法），开启了静默转换，且设置了默认值，应打印异常，并将默认值包装为 PageResult<默认值> 并返回，注意观察日志是否正确
        Assert.assertEquals(CollectionsUtil.getFirst(Converts.withMybatisPlusMapstruct().toPageBean(DEFAULT_PAGE, StudentDO.class, DEFAULT_STUDENT_DO, true).getData()), DEFAULT_STUDENT_DO);

        // ================ 测试转换 null ================
        Assert.assertEquals(0, Converts.withMybatisPlusMapstruct().toPageBean(null, null).getData().size());
        Assert.assertEquals(0, Converts.withMybatisPlusMapstruct().toPageBean(null, null, null, null).getData().size());
    }

}
