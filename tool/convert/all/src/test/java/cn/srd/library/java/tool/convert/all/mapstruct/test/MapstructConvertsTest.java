// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.all.mapstruct.test;

import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.convert.all.mapstruct.model.domain.GradeDO;
import cn.srd.library.java.tool.convert.all.mapstruct.model.domain.StudentDO;
import cn.srd.library.java.tool.convert.all.mapstruct.model.domain.StudentUnsupportedMapstructConvertDO;
import cn.srd.library.java.tool.convert.all.mapstruct.model.vo.GradeVO;
import cn.srd.library.java.tool.convert.all.mapstruct.model.vo.StudentUnsupportedMapstructConvertVO;
import cn.srd.library.java.tool.convert.all.mapstruct.model.vo.StudentVO;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.object.Nil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MapstructConvertsTest {

    private static final StudentVO DEFAULT_STUDENT_VO = StudentVO.newVO();

    private static final StudentUnsupportedMapstructConvertDO DEFAULT_STUDENT_UNSUPPORTED_CONVERT_DO = StudentUnsupportedMapstructConvertDO.builder().build();

    @Test
    void testMapstructConverts() {
        // ================ 测试 普通 Bean 一对一转换 ================
        // 转换成功
        Assertions.assertNotNull(Converts.withGenericMapstruct().toBean(StudentDO.newDO(), StudentVO.class).getStudentId());
        // 转换带默认值，源对象为 null 的情况下返回默认值
        Assertions.assertEquals(DEFAULT_STUDENT_VO, Converts.withGenericMapstruct().toBean(null, StudentVO.class, DEFAULT_STUDENT_VO));
        // 转换失败（转换结果类型与提供的目标值类型不一致，只有一对一转换时会出现这种情况，因为存在第一层缓存，此时未用转换方法出参进行匹配），未开启静默转换（就算开启了静默转换，由于是到了赋值阶段才会抛出 ClassCastException，因此无法在转换内部捕获，所以该情况开启静默转换无效，应由调用者注意别写错了），应在赋值阶段抛出异常
        Assertions.assertThrowsExactly(ClassCastException.class, () -> Converts.withGenericMapstruct().toBean(GradeDO.newDO(), StudentVO.class));
        // 转换失败（未匹配到转换方法），未开启静默转换，应抛出异常
        Assertions.assertThrowsExactly(LibraryJavaInternalException.class, () -> Converts.withGenericMapstruct().toBean(StudentVO.newVO(), StudentDO.class));
        // 转换失败（未匹配到转换方法），开启了静默转换，且没有设置默认值，应打印异常并返回 null，注意观察日志是否正确
        Assertions.assertNull(Converts.withGenericMapstruct().toBean(StudentVO.newVO(), StudentDO.class, true));
        // 转换失败（未匹配到转换方法），开启了静默转换，且设置了默认值，应打印异常并返回默认值，注意观察日志是否正确
        Assertions.assertEquals(DEFAULT_STUDENT_VO, Converts.withGenericMapstruct().toBean(StudentVO.newVO(), StudentVO.class, DEFAULT_STUDENT_VO, true));

        // ================ 测试 普通 Bean 二对一转换 ================
        // 转换成功
        Assertions.assertNotNull(Converts.withGenericMapstruct().toBean(StudentDO.newDO(), GradeDO.newDO(), StudentVO.class).getStudentId());
        // 转换带默认值，源对象为 null 的情况下返回默认值
        Assertions.assertEquals(DEFAULT_STUDENT_VO, Converts.withGenericMapstruct().toBean(null, null, StudentVO.class, DEFAULT_STUDENT_VO));
        // 转换失败（未匹配到转换方法），未开启静默转换，应抛出异常
        Assertions.assertThrowsExactly(LibraryJavaInternalException.class, () -> Converts.withGenericMapstruct().toBean(StudentVO.newVO(), StudentVO.newVO(), StudentDO.class));
        // 转换失败（未匹配到转换方法），开启了静默转换，且没有设置默认值，应打印异常并返回 null，注意观察日志是否正确
        Assertions.assertNull(Converts.withGenericMapstruct().toBean(StudentVO.newVO(), StudentVO.newVO(), StudentDO.class, true));
        // 转换失败（未匹配到转换方法），开启了静默转换，且设置了默认值，应打印异常并返回默认值，注意观察日志是否正确
        Assertions.assertEquals(DEFAULT_STUDENT_VO, Converts.withGenericMapstruct().toBean(StudentVO.newVO(), StudentVO.newVO(), StudentVO.class, DEFAULT_STUDENT_VO, true));

        // ================ 测试 普通 Bean 三对一转换 ================
        // 转换成功
        Assertions.assertNotNull(Converts.withGenericMapstruct().toBean(StudentDO.newDO(), GradeDO.newDO(), GradeDO.newDO(), StudentVO.class).getStudentId());
        // 转换带默认值，源对象为 null 的情况下返回默认值
        Assertions.assertEquals(DEFAULT_STUDENT_VO, Converts.withGenericMapstruct().toBean(null, null, null, StudentVO.class, DEFAULT_STUDENT_VO));
        // 转换失败（未匹配到转换方法），未开启静默转换，应抛出异常
        Assertions.assertThrowsExactly(LibraryJavaInternalException.class, () -> Converts.withGenericMapstruct().toBean(StudentVO.newVO(), StudentVO.newVO(), StudentVO.newVO(), StudentDO.class));
        // 转换失败（未匹配到转换方法），开启了静默转换，且没有设置默认值，应打印异常并返回 null，注意观察日志是否正确
        Assertions.assertNull(Converts.withGenericMapstruct().toBean(StudentVO.newVO(), StudentVO.newVO(), StudentVO.newVO(), StudentDO.class, true));
        // 转换失败（未匹配到转换方法），开启了静默转换，且设置了默认值，应打印异常并返回默认值，注意观察日志是否正确
        Assertions.assertEquals(DEFAULT_STUDENT_VO, Converts.withGenericMapstruct().toBean(StudentVO.newVO(), StudentVO.newVO(), StudentVO.newVO(), StudentVO.class, DEFAULT_STUDENT_VO, true));

        // ================ 测试 List Bean 一对一转换 ================
        // 转换成功
        Assertions.assertTrue(Nil.isNotEmpty(Converts.withGenericMapstruct().toBeans(StudentDO.newDOs(), StudentVO.class)));
        // 转换成功（用于测试直接定义在 Mapstruct 转换器中的 default 方法能否找到）
        Assertions.assertTrue(Nil.isNotEmpty(Converts.withGenericMapstruct().toBeans(GradeDO.newDOs(), GradeVO.class)));
        // 转换带默认值，源对象为 null 的情况下，将默认值包装为 ArrayList<默认值> 并返回
        Assertions.assertEquals(DEFAULT_STUDENT_VO, Collections.getFirst(Converts.withGenericMapstruct().toBeans(null, StudentVO.class, DEFAULT_STUDENT_VO)).orElseThrow());
        // 转换带默认值，源对象为 empty 的情况下，将默认值包装为 ArrayList<默认值> 并返回
        Assertions.assertEquals(DEFAULT_STUDENT_VO, Collections.getFirst(Converts.withGenericMapstruct().toBeans(new ArrayList<>(), StudentVO.class, DEFAULT_STUDENT_VO)).orElseThrow());
        // 转换失败（未匹配到转换方法），未开启静默转换，应抛出异常
        Assertions.assertThrowsExactly(LibraryJavaInternalException.class, () -> Converts.withGenericMapstruct().toBeans(StudentVO.newVOs(), StudentVO.class));
        // 转换失败（未匹配到转换方法），开启了静默转换，且没有设置默认值，应打印异常并返回空的 ArrayList，注意观察日志是否正确
        Assertions.assertEquals(0, Converts.withGenericMapstruct().toBeans(StudentVO.newVOs(), StudentVO.class, true).size());
        // 转换失败（未匹配到转换方法），开启了静默转换，且设置了默认值，应打印异常，并将默认值包装为 ArrayList<默认值> 并返回，注意观察日志是否正确
        Assertions.assertEquals(DEFAULT_STUDENT_VO, Collections.getFirst(Converts.withGenericMapstruct().toBeans(StudentVO.newVOs(), StudentVO.class, DEFAULT_STUDENT_VO, true)).orElseThrow());

        // ================ 测试在 Mapstruct 转换器中定义了相同出入参的方法时的处理是否符合预期 ================
        // 开启了静默转换，且没有设置默认值，应打印异常并返回 null，注意观察日志是否正确
        Assertions.assertNull(Converts.withGenericMapstruct().toBean(StudentUnsupportedMapstructConvertVO.newVO(), StudentUnsupportedMapstructConvertDO.class, true));
        // 开启了静默转换，且设置了默认值，应打印异常并返回默认值，注意观察日志是否正确
        Assertions.assertEquals(DEFAULT_STUDENT_UNSUPPORTED_CONVERT_DO, Converts.withGenericMapstruct().toBean(StudentUnsupportedMapstructConvertVO.newVO(), StudentUnsupportedMapstructConvertDO.class, DEFAULT_STUDENT_UNSUPPORTED_CONVERT_DO, true));
        // 未开启静默转换，应抛出异常
        Assertions.assertThrowsExactly(LibraryJavaInternalException.class, () -> Converts.withGenericMapstruct().toBean(StudentUnsupportedMapstructConvertVO.newVO(), StudentUnsupportedMapstructConvertDO.class));

        // ================ 测试转换 null 是否报错 ================
        Assertions.assertNull(Converts.withGenericMapstruct().toBean(null, null));
        Assertions.assertNull(Converts.withGenericMapstruct().toBean(null, null, null, null, null, null));
        Assertions.assertNotNull(Converts.withGenericMapstruct().toBeans(null, null));
        Assertions.assertNotNull(Converts.withGenericMapstruct().toBeans(null, null, null, null));
        Assertions.assertEquals(0, Converts.withGenericMapstruct().toBeans(null, null).size());
        Assertions.assertEquals(0, Converts.withGenericMapstruct().toBeans(null, null, null, null).size());
    }

}