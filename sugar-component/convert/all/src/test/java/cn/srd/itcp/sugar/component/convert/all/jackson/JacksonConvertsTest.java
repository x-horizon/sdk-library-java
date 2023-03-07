package cn.srd.itcp.sugar.component.convert.all.jackson;

import cn.srd.itcp.sugar.component.convert.all.core.Converts;
import cn.srd.itcp.sugar.component.convert.all.jackson.bean.GradeDO;
import cn.srd.itcp.sugar.component.convert.all.jackson.bean.StudentDO;
import cn.srd.itcp.sugar.component.convert.jackson.core.JacksonConverts;
import cn.srd.itcp.sugar.tool.core.FilesUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import cn.srd.itcp.sugar.tool.core.ReflectsUtil;
import cn.srd.itcp.sugar.tool.core.StringsUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.ImmutableMap;
import jakarta.validation.ConstraintViolationException;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JacksonConvertsTest {

    private static final StudentDO STUDENT_DO = StudentDO.newDO();
    private static final List<StudentDO> STUDENT_DOS = StudentDO.newDOs();

    private static final GradeDO GRADE_DO = GradeDO.newDO();
    private static final List<GradeDO> GRADE_DOS = GradeDO.newDOs();

    private static final String KEY = "Grade";
    private static final Map<String, GradeDO> STRING_MAPPING_GRADE_DO_MAP = ImmutableMap.of(KEY, GRADE_DO);
    private static final Map<String, List<GradeDO>> STRING_MAPPING_GRADE_DOS_MAP = ImmutableMap.of(KEY, GRADE_DOS);
    private static final Map<StudentDO, GradeDO> STUDENT_DO_MAPPING_GRADE_DO_MAP = ImmutableMap.of(STUDENT_DO, GRADE_DO);
    private static final Map<List<StudentDO>, List<GradeDO>> STUDENT_DOS_MAPPING_GRADE_DOS_MAP = ImmutableMap.of(STUDENT_DOS, GRADE_DOS);

    @Test
    @SneakyThrows
    public void testJacksonConverts() {
        String gradeDOJson = Converts.withJackson().toString(GRADE_DO);
        String gradeDOsJsonArray = Converts.withJackson().toString(GRADE_DOS);
        String stringMappingGradeDOMapJson = Converts.withJackson().toString(STRING_MAPPING_GRADE_DO_MAP);
        String stringMappingGradeDOsMapJson = Converts.withJackson().toString(STRING_MAPPING_GRADE_DOS_MAP);
        String studentDOMappingGradeDOMapJson = Converts.withJackson().toString(STUDENT_DO_MAPPING_GRADE_DO_MAP);
        String studentDOsMappingGradeDOsMapJson = Converts.withJackson().toString(STUDENT_DOS_MAPPING_GRADE_DOS_MAP);
        Assert.assertTrue(Objects.isNotBlank(gradeDOJson));
        Assert.assertTrue(Objects.isNotBlank(gradeDOsJsonArray));
        Assert.assertTrue(Objects.isNotBlank(stringMappingGradeDOMapJson));
        Assert.assertTrue(Objects.isNotBlank(stringMappingGradeDOsMapJson));
        Assert.assertTrue(Objects.isNotBlank(studentDOMappingGradeDOMapJson));
        Assert.assertTrue(Objects.isNotBlank(studentDOsMappingGradeDOsMapJson));

        Assert.assertTrue(Objects.isNotBlank(Converts.withJackson().toJsonNode(gradeDOJson).get("name").asText()));

        Assert.assertNotNull(Converts.withJackson().toAnything(gradeDOJson, new TypeReference<GradeDO>() {
        }));
        Assert.assertTrue(Objects.isNotEmpty(Converts.withJackson().toAnything(gradeDOsJsonArray, new TypeReference<List<GradeDO>>() {
        })));
        Assert.assertTrue(Objects.isNotEmpty(Converts.withJackson().toAnything(stringMappingGradeDOMapJson, new TypeReference<Map<String, GradeDO>>() {
        })));
        Assert.assertTrue(Objects.isNotEmpty(Converts.withJackson().toAnything(stringMappingGradeDOsMapJson, new TypeReference<Map<String, List<GradeDO>>>() {
        })));

        Assert.assertNotNull(Converts.withJackson().toBean(gradeDOJson, GradeDO.class));
        Assert.assertTrue(Objects.isNotEmpty(Converts.withJackson().toBeans(gradeDOsJsonArray, GradeDO.class)));
        Assert.assertTrue(Objects.isNotEmpty(Converts.withJackson().toMap(stringMappingGradeDOMapJson, GradeDO.class)));
        Assert.assertTrue(Objects.isNotEmpty(Converts.withJackson().toMaps(stringMappingGradeDOsMapJson, GradeDO.class)));

        Assert.assertThrows(ConstraintViolationException.class, () -> Converts.withJackson().toBean(gradeDOJson, GradeDO.class, true));
        Assert.assertThrows(ConstraintViolationException.class, () -> Converts.withJackson().toBeans(gradeDOsJsonArray, GradeDO.class, true));
        Assert.assertThrows(ConstraintViolationException.class, () -> Converts.withJackson().toMap(stringMappingGradeDOMapJson, GradeDO.class, true));
        Assert.assertThrows(ConstraintViolationException.class, () -> Converts.withJackson().toMaps(stringMappingGradeDOsMapJson, GradeDO.class, true));

        String path = "test.json";
        File file = new File(path);
        URL url = new URL("file:" + path);

        Converts.withJackson().writeToFile(file, GRADE_DO);
        Assert.assertTrue(FilesUtil.exist(file));
        Assert.assertNotNull(Converts.withJackson().toBean(file, GradeDO.class));
        Assert.assertNotNull(Converts.withJackson().toBean(url, GradeDO.class));
        Assert.assertNotNull(Converts.withJackson().toAnything(file, new TypeReference<GradeDO>() {
        }));
        Assert.assertNotNull(Converts.withJackson().toAnything(url, new TypeReference<GradeDO>() {
        }));
        FilesUtil.del(file);

        Converts.withJackson().writeToFile(file, GRADE_DOS);
        Assert.assertTrue(FilesUtil.exist(file));
        Assert.assertNotNull(Converts.withJackson().toBeans(file, GradeDO.class));
        Assert.assertNotNull(Converts.withJackson().toBeans(url, GradeDO.class));
        Assert.assertTrue(Objects.isNotEmpty(Converts.withJackson().toAnything(file, new TypeReference<List<GradeDO>>() {
        })));
        Assert.assertTrue(Objects.isNotEmpty(Converts.withJackson().toAnything(url, new TypeReference<List<GradeDO>>() {
        })));
        FilesUtil.del(file);

        Converts.withJackson().writeToFile(file, STRING_MAPPING_GRADE_DO_MAP);
        Assert.assertTrue(FilesUtil.exist(file));
        Assert.assertNotNull(Converts.withJackson().toMap(file, GradeDO.class));
        Assert.assertNotNull(Converts.withJackson().toMap(url, GradeDO.class));
        Assert.assertTrue(Objects.isNotEmpty(Converts.withJackson().toAnything(file, new TypeReference<Map<String, GradeDO>>() {
        })));
        Assert.assertTrue(Objects.isNotEmpty(Converts.withJackson().toAnything(url, new TypeReference<Map<String, GradeDO>>() {
        })));
        FilesUtil.del(file);

        Converts.withJackson().writeToFile(file, STRING_MAPPING_GRADE_DOS_MAP);
        Assert.assertTrue(FilesUtil.exist(file));
        Assert.assertNotNull(Converts.withJackson().toMaps(file, GradeDO.class));
        Assert.assertNotNull(Converts.withJackson().toMaps(url, GradeDO.class));
        Assert.assertTrue(Objects.isNotEmpty(Converts.withJackson().toAnything(file, new TypeReference<Map<String, List<GradeDO>>>() {
        })));
        Assert.assertTrue(Objects.isNotEmpty(Converts.withJackson().toAnything(url, new TypeReference<Map<String, List<GradeDO>>>() {
        })));
        FilesUtil.del(file);
    }

    @Test
    @SneakyThrows
    public void testReplaceJacksonGlobalJacksonMapperByMultiThread() {
        int threadCount = 1000;
        AtomicInteger passThread = new AtomicInteger();
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch endSignal = new CountDownLatch(threadCount);
        for (int i = 1; i <= threadCount; ++i) {
            new Thread(() -> {
                try {
                    startSignal.await();
                    try {
                        Converts.withJackson().builder().buildGlobal();
                        passThread.getAndIncrement();
                    } catch (RuntimeException ignore) {
                    }
                    endSignal.countDown();
                } catch (InterruptedException ignore) {
                }
            }).start();
        }
        startSignal.countDown();
        endSignal.await();
        Assert.assertEquals(passThread.get(), 1);
        restoreGlobalJacksonMapperToAllowReplaceAgain();
    }

    @Test
    @SneakyThrows
    public void testCustomizeLocalAndGlobalJacksonConverts() {
        Assert.assertTrue(StringsUtil.containsBlank(Converts.withJackson()
                .builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .build()
                .getConverts()
                .toString(STUDENT_DO))
        );

        Assert.assertFalse(StringsUtil.containsBlank(Converts.withJackson().toString(STUDENT_DO)));

        Converts.withJackson()
                .builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .buildGlobal();
        Assert.assertTrue(StringsUtil.containsBlank(Converts.withJackson().toString(STUDENT_DO)));
        restoreGlobalJacksonMapperToAllowReplaceAgain();
    }

    private void restoreGlobalJacksonMapperToAllowReplaceAgain() {
        ReflectsUtil.setFieldValue(JacksonConverts.JacksonMapper.Builder.class, "allowToReplaceGlobalObjectMapper", Boolean.TRUE);
    }

}
