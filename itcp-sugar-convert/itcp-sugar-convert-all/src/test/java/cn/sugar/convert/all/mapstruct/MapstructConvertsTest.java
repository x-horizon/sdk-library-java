package cn.sugar.convert.all.mapstruct;

import cn.sugar.convert.all.core.Converts;
import cn.sugar.convert.all.mapstruct.bean.domain.GradeDO;
import cn.sugar.convert.all.mapstruct.bean.domain.StudentDO;
import cn.sugar.convert.all.mapstruct.bean.domain.StudentUnsupportedMapstructConvertDO;
import cn.sugar.convert.all.mapstruct.bean.vo.GradeVO;
import cn.sugar.convert.all.mapstruct.bean.vo.StudentUnsupportedMapstructConvertVO;
import cn.sugar.convert.all.mapstruct.bean.vo.StudentVO;
import cn.sugar.convert.mapstruct.exception.MapstructConvertMethodNotFoundException;
import cn.sugar.convert.mapstruct.exception.MapstructConvertMethodUnsupportedException;
import cn.sugar.tools.core.CollectionsUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapstructConvertsTest {

    private static final StudentVO DEFAULT_STUDENT_VO = StudentVO.newVO();
    private static final StudentUnsupportedMapstructConvertDO DEFAULT_STUDENT_UNSUPPORTED_CONVERT_DO = new StudentUnsupportedMapstructConvertDO();

    @Test
    public void testMapstructConverts() {
        // ================ 测试 普通 Bean 一对一转换 ================
        // 转换成功
        Assert.assertNotNull(Converts.withGenericMapstruct().toBean(StudentDO.newDO(), StudentVO.class).getStudentId());
        // 转换带默认值，源对象为 null 的情况下返回默认值
        Assert.assertEquals(Converts.withGenericMapstruct().toBean(null, StudentVO.class, DEFAULT_STUDENT_VO), DEFAULT_STUDENT_VO);
        // 转换失败（转换结果类型与提供的目标值类型不一致，只有一对一转换时会出现这种情况，因为存在第一层缓存，此时未用转换方法出参进行匹配），未开启静默转换（就算开启了静默转换，由于是到了赋值阶段才会抛出 ClassCastException，因此无法在转换内部捕获，所以该情况开启静默转换无效，应由调用者注意别写错了），应在赋值阶段抛出异常
        Assert.assertThrows(ClassCastException.class, () -> {
            StudentVO studentVO = Converts.withGenericMapstruct().toBean(GradeDO.newDO(), StudentVO.class);
        });
        // 转换失败（未匹配到转换方法），未开启静默转换，应抛出异常
        Assert.assertThrows(MapstructConvertMethodNotFoundException.class, () -> Converts.withGenericMapstruct().toBean(StudentVO.newVO(), StudentDO.class));
        // 转换失败（未匹配到转换方法），开启了静默转换，且没有设置默认值，应打印异常并返回 null，注意观察日志是否正确
        Assert.assertNull(Converts.withGenericMapstruct().toBean(StudentVO.newVO(), StudentDO.class, true));
        // 转换失败（未匹配到转换方法），开启了静默转换，且设置了默认值，应打印异常并返回默认值，注意观察日志是否正确
        Assert.assertEquals(Converts.withGenericMapstruct().toBean(StudentVO.newVO(), StudentVO.class, DEFAULT_STUDENT_VO, true), DEFAULT_STUDENT_VO);

        // ================ 测试 普通 Bean 二对一转换 ================
        // 转换成功
        Assert.assertNotNull(Converts.withGenericMapstruct().toBean(StudentDO.newDO(), GradeDO.newDO(), StudentVO.class).getStudentId());
        // 转换带默认值，源对象为 null 的情况下返回默认值
        Assert.assertEquals(Converts.withGenericMapstruct().toBean(null, null, StudentVO.class, DEFAULT_STUDENT_VO), DEFAULT_STUDENT_VO);
        // 转换失败（未匹配到转换方法），未开启静默转换，应抛出异常
        Assert.assertThrows(MapstructConvertMethodNotFoundException.class, () -> Converts.withGenericMapstruct().toBean(StudentVO.newVO(), StudentVO.newVO(), StudentDO.class));
        // 转换失败（未匹配到转换方法），开启了静默转换，且没有设置默认值，应打印异常并返回 null，注意观察日志是否正确
        Assert.assertNull(Converts.withGenericMapstruct().toBean(StudentVO.newVO(), StudentVO.newVO(), StudentDO.class, true));
        // 转换失败（未匹配到转换方法），开启了静默转换，且设置了默认值，应打印异常并返回默认值，注意观察日志是否正确
        Assert.assertEquals(Converts.withGenericMapstruct().toBean(StudentVO.newVO(), StudentVO.newVO(), StudentVO.class, DEFAULT_STUDENT_VO, true), DEFAULT_STUDENT_VO);

        // ================ 测试 普通 Bean 三对一转换 ================
        // 转换成功
        Assert.assertNotNull(Converts.withGenericMapstruct().toBean(StudentDO.newDO(), GradeDO.newDO(), GradeDO.newDO(), StudentVO.class).getStudentId());
        // 转换带默认值，源对象为 null 的情况下返回默认值
        Assert.assertEquals(Converts.withGenericMapstruct().toBean(null, null, null, StudentVO.class, DEFAULT_STUDENT_VO), DEFAULT_STUDENT_VO);
        // 转换失败（未匹配到转换方法），未开启静默转换，应抛出异常
        Assert.assertThrows(MapstructConvertMethodNotFoundException.class, () -> Converts.withGenericMapstruct().toBean(StudentVO.newVO(), StudentVO.newVO(), StudentVO.newVO(), StudentDO.class));
        // 转换失败（未匹配到转换方法），开启了静默转换，且没有设置默认值，应打印异常并返回 null，注意观察日志是否正确
        Assert.assertNull(Converts.withGenericMapstruct().toBean(StudentVO.newVO(), StudentVO.newVO(), StudentVO.newVO(), StudentDO.class, true));
        // 转换失败（未匹配到转换方法），开启了静默转换，且设置了默认值，应打印异常并返回默认值，注意观察日志是否正确
        Assert.assertEquals(Converts.withGenericMapstruct().toBean(StudentVO.newVO(), StudentVO.newVO(), StudentVO.newVO(), StudentVO.class, DEFAULT_STUDENT_VO, true), DEFAULT_STUDENT_VO);

        // ================ 测试 List Bean 一对一转换 ================
        // 转换成功
        Assert.assertTrue(Converts.withGenericMapstruct().toBeans(StudentDO.newDOs(), StudentVO.class).size() > 0);
        // 转换成功（用于测试直接定义在 Mapstruct 转换器中的 default 方法能否找到）
        Assert.assertTrue(Converts.withGenericMapstruct().toBeans(GradeDO.newDOs(), GradeVO.class).size() > 0);
        // 转换带默认值，源对象为 null 的情况下，将默认值包装为 ArrayList<默认值> 并返回
        Assert.assertEquals(CollectionsUtil.getFirst(Converts.withGenericMapstruct().toBeans(null, StudentVO.class, DEFAULT_STUDENT_VO)), DEFAULT_STUDENT_VO);
        // 转换带默认值，源对象为 empty 的情况下，将默认值包装为 ArrayList<默认值> 并返回
        Assert.assertEquals(CollectionsUtil.getFirst(Converts.withGenericMapstruct().toBeans(new ArrayList<>(), StudentVO.class, DEFAULT_STUDENT_VO)), DEFAULT_STUDENT_VO);
        // 转换失败（未匹配到转换方法），未开启静默转换，应抛出异常
        Assert.assertThrows(MapstructConvertMethodNotFoundException.class, () -> Converts.withGenericMapstruct().toBeans(StudentVO.newVOs(), StudentVO.class));
        // 转换失败（未匹配到转换方法），开启了静默转换，且没有设置默认值，应打印异常并返回空的 ArrayList，注意观察日志是否正确
        Assert.assertEquals(0, Converts.withGenericMapstruct().toBeans(StudentVO.newVOs(), StudentVO.class, true).size());
        // 转换失败（未匹配到转换方法），开启了静默转换，且设置了默认值，应打印异常，并将默认值包装为 ArrayList<默认值> 并返回，注意观察日志是否正确
        Assert.assertEquals(CollectionsUtil.getFirst(Converts.withGenericMapstruct().toBeans(StudentVO.newVOs(), StudentVO.class, DEFAULT_STUDENT_VO, true)), DEFAULT_STUDENT_VO);

        // ================ 测试在 Mapstruct 转换器中定义了相同出入参的方法时的处理是否符合预期 ================
        // 开启了静默转换，且没有设置默认值，应打印异常并返回 null，注意观察日志是否正确
        Assert.assertNull(Converts.withGenericMapstruct().toBean(StudentUnsupportedMapstructConvertVO.newVO(), StudentUnsupportedMapstructConvertDO.class, true));
        // 开启了静默转换，且设置了默认值，应打印异常并返回默认值，注意观察日志是否正确
        Assert.assertEquals(Converts.withGenericMapstruct().toBean(StudentUnsupportedMapstructConvertVO.newVO(), StudentUnsupportedMapstructConvertDO.class, DEFAULT_STUDENT_UNSUPPORTED_CONVERT_DO, true), DEFAULT_STUDENT_UNSUPPORTED_CONVERT_DO);
        // 未开启静默转换，应抛出异常
        Assert.assertThrows(MapstructConvertMethodUnsupportedException.class, () -> Converts.withGenericMapstruct().toBean(StudentUnsupportedMapstructConvertVO.newVO(), StudentUnsupportedMapstructConvertDO.class));

        // ================ 测试转换 null 是否报错 ================
        Assert.assertNull(Converts.withGenericMapstruct().toBean(null, null));
        Assert.assertNull(Converts.withGenericMapstruct().toBean(null, null, null, null, null, null));
        Assert.assertNotNull(Converts.withGenericMapstruct().toBeans(null, null));
        Assert.assertNotNull(Converts.withGenericMapstruct().toBeans(null, null, null, null));
        Assert.assertEquals(0, Converts.withGenericMapstruct().toBeans(null, null).size());
        Assert.assertEquals(0, Converts.withGenericMapstruct().toBeans(null, null, null, null).size());
    }

}
