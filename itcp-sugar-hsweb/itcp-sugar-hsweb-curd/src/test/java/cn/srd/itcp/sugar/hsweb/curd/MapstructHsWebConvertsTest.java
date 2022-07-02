package cn.srd.itcp.sugar.hsweb.curd;

import cn.itcp.srd.sugar.convert.mapstruct.exception.MapstructConvertMethodNotFoundException;
import cn.srd.itcp.sugar.hsweb.curd.bean.domain.StudentDO;
import cn.srd.itcp.sugar.hsweb.curd.bean.vo.StudentVO;
import cn.srd.itcp.sugar.hsweb.curd.utils.Converts;
import cn.srd.itcp.sugar.tools.core.CollectionsUtil;
import org.hswebframework.web.api.crud.entity.PagerResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapstructHsWebConvertsTest {

    private static final StudentVO DEFAULT_STUDENT_VO = StudentVO.newVO();
    private static final StudentDO DEFAULT_STUDENT_DO = StudentDO.newDO();
    private static final List<StudentDO> DEFAULT_STUDENT_DOS = StudentDO.newDOs();
    private static final PagerResult<StudentDO> DEFAULT_PAGE = new PagerResult<>();

    static {
        DEFAULT_PAGE.setPageIndex(1);
        DEFAULT_PAGE.setData(DEFAULT_STUDENT_DOS);
        DEFAULT_PAGE.setPageSize(3);
        DEFAULT_PAGE.setTotal(3);
    }

    @Test
    public void testMybatisPlusMapstructBeanConverter() {
        // 转换成功
        Assert.assertNotNull(Converts.withHsWebMapstruct().toPageBean(DEFAULT_PAGE, StudentVO.class).getData().get(0));
        // 转换带默认值，源对象为 null 的情况下，将默认值包装为 PageResult<默认值> 并返回
        Assert.assertNotNull(Converts.withHsWebMapstruct().toPageBean(null, StudentVO.class, DEFAULT_STUDENT_VO).getData().get(0));
        // 转换失败（未匹配到转换方法），未开启静默转换，应抛出异常
        Assert.assertThrows(MapstructConvertMethodNotFoundException.class, () -> Converts.withHsWebMapstruct().toPageBean(DEFAULT_PAGE, StudentDO.class));
        // 转换失败（未匹配到转换方法），开启了静默转换，且没有设置默认值，应打印异常并返回空的 PageResult，注意观察日志是否正确
        Assert.assertEquals(0, Converts.withHsWebMapstruct().toPageBean(DEFAULT_PAGE, StudentDO.class, true).getData().size());
        // 转换失败（未匹配到转换方法），开启了静默转换，且设置了默认值，应打印异常，并将默认值包装为 PageResult<默认值> 并返回，注意观察日志是否正确
        Assert.assertEquals(CollectionsUtil.getFirst(Converts.withHsWebMapstruct().toPageBean(DEFAULT_PAGE, StudentDO.class, DEFAULT_STUDENT_DO, true).getData()), DEFAULT_STUDENT_DO);

        // ================ 测试转换 null ================
        Assert.assertEquals(0, Converts.withHsWebMapstruct().toPageBean(null, null).getData().size());
        Assert.assertEquals(0, Converts.withHsWebMapstruct().toPageBean(null, null, null, null).getData().size());
    }

}
