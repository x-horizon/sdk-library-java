package cn.library.java.tool.convert.api.jackson.test;

import cn.library.java.tool.convert.api.Converts;
import cn.library.java.tool.convert.api.jackson.model.GradeDO;
import cn.library.java.tool.convert.api.jackson.model.StudentDO;
import cn.library.java.tool.convert.jackson.JacksonConverts;
import cn.library.java.tool.convert.jackson.Jacksons;
import cn.library.java.tool.lang.file.Files;
import cn.library.java.tool.lang.object.Nil;
import cn.library.java.tool.lang.reflect.Reflects;
import cn.library.java.tool.lang.text.Strings;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.ImmutableMap;
import jakarta.validation.ConstraintViolationException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class JacksonConvertsTest {

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
    void testJacksonConvertsToByte() {
        byte[] gradeDOJson = Converts.onJackson().toBytes(GRADE_DO);
        Converts.onJackson().toBean(gradeDOJson, GradeDO.class);
        byte[] gradeDOsJsonArray = Converts.onJackson().toBytes(GRADE_DOS);
        Converts.onJackson().toBeans(gradeDOsJsonArray, GradeDO.class);
    }

    @SneakyThrows
    @Test
    void testJacksonConverts() {
        String gradeDOJson = Converts.onJackson().toString(GRADE_DO);
        String gradeDOsJsonArray = Converts.onJackson().toString(GRADE_DOS);
        String stringMappingGradeDOMapJson = Converts.onJackson().toString(STRING_MAPPING_GRADE_DO_MAP);
        String stringMappingGradeDOsMapJson = Converts.onJackson().toString(STRING_MAPPING_GRADE_DOS_MAP);
        String studentDOMappingGradeDOMapJson = Converts.onJackson().toString(STUDENT_DO_MAPPING_GRADE_DO_MAP);
        String studentDOsMappingGradeDOsMapJson = Converts.onJackson().toString(STUDENT_DOS_MAPPING_GRADE_DOS_MAP);
        Assertions.assertTrue(Nil.isNotBlank(gradeDOJson));
        Assertions.assertTrue(Nil.isNotBlank(gradeDOsJsonArray));
        Assertions.assertTrue(Nil.isNotBlank(stringMappingGradeDOMapJson));
        Assertions.assertTrue(Nil.isNotBlank(stringMappingGradeDOsMapJson));
        Assertions.assertTrue(Nil.isNotBlank(studentDOMappingGradeDOMapJson));
        Assertions.assertTrue(Nil.isNotBlank(studentDOsMappingGradeDOsMapJson));

        Assertions.assertTrue(Nil.isNotBlank(Converts.onJackson().toJsonNode(gradeDOJson).get("name").asText()));

        Assertions.assertNotNull(Converts.onJackson().toAnything(gradeDOJson, Jacksons.<GradeDO>newTypeReference()));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.onJackson().toAnything(gradeDOsJsonArray, Jacksons.<List<GradeDO>>newTypeReference())));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.onJackson().toAnything(stringMappingGradeDOMapJson, Jacksons.<Map<String, GradeDO>>newTypeReference())));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.onJackson().toAnything(stringMappingGradeDOsMapJson, Jacksons.<Map<String, List<GradeDO>>>newTypeReference())));

        Assertions.assertNotNull(Converts.onJackson().toBean(gradeDOJson, GradeDO.class));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.onJackson().toBeans(gradeDOsJsonArray, GradeDO.class)));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.onJackson().toMap(stringMappingGradeDOMapJson, GradeDO.class)));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.onJackson().toMaps(stringMappingGradeDOsMapJson, GradeDO.class)));

        Assertions.assertThrowsExactly(ConstraintViolationException.class, () -> Converts.onJackson().toBean(gradeDOJson, GradeDO.class, true));
        Assertions.assertThrowsExactly(ConstraintViolationException.class, () -> Converts.onJackson().toBeans(gradeDOsJsonArray, GradeDO.class, true));
        Assertions.assertThrowsExactly(ConstraintViolationException.class, () -> Converts.onJackson().toMap(stringMappingGradeDOMapJson, GradeDO.class, true));
        Assertions.assertThrowsExactly(ConstraintViolationException.class, () -> Converts.onJackson().toMaps(stringMappingGradeDOsMapJson, GradeDO.class, true));

        String path = "test.json";
        File file = new File(path);
        URL url = URI.create("file:" + path).toURL();

        Converts.onJackson().writeToFile(file, GRADE_DO);
        Assertions.assertTrue(Files.exist(file));
        Assertions.assertNotNull(Converts.onJackson().toBean(file, GradeDO.class));
        Assertions.assertNotNull(Converts.onJackson().toBean(url, GradeDO.class));
        Assertions.assertNotNull(Converts.onJackson().toAnything(file, Jacksons.<GradeDO>newTypeReference()));
        Assertions.assertNotNull(Converts.onJackson().toAnything(url, Jacksons.<GradeDO>newTypeReference()));
        Files.delete(file);

        Converts.onJackson().writeToFile(file, GRADE_DOS);
        Assertions.assertTrue(Files.exist(file));
        Assertions.assertNotNull(Converts.onJackson().toBeans(file, GradeDO.class));
        Assertions.assertNotNull(Converts.onJackson().toBeans(url, GradeDO.class));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.onJackson().toAnything(file, Jacksons.<List<GradeDO>>newTypeReference())));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.onJackson().toAnything(url, Jacksons.<List<GradeDO>>newTypeReference())));
        Files.delete(file);

        Converts.onJackson().writeToFile(file, STRING_MAPPING_GRADE_DO_MAP);
        Assertions.assertTrue(Files.exist(file));
        Assertions.assertNotNull(Converts.onJackson().toMap(file, GradeDO.class));
        Assertions.assertNotNull(Converts.onJackson().toMap(url, GradeDO.class));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.onJackson().toAnything(file, Jacksons.<Map<String, GradeDO>>newTypeReference())));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.onJackson().toAnything(url, Jacksons.<Map<String, GradeDO>>newTypeReference())));
        Files.delete(file);

        Converts.onJackson().writeToFile(file, STRING_MAPPING_GRADE_DOS_MAP);
        Assertions.assertTrue(Files.exist(file));
        Assertions.assertNotNull(Converts.onJackson().toMaps(file, GradeDO.class));
        Assertions.assertNotNull(Converts.onJackson().toMaps(url, GradeDO.class));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.onJackson().toAnything(file, Jacksons.<Map<String, List<GradeDO>>>newTypeReference())));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.onJackson().toAnything(url, Jacksons.<Map<String, List<GradeDO>>>newTypeReference())));
        Files.delete(file);
    }

    @SneakyThrows
    @Test
    void testReplaceJacksonGlobalJacksonMapperByMultiThread() {
        int threadCount = 1000;
        AtomicInteger passThread = new AtomicInteger();
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch endSignal = new CountDownLatch(threadCount);
        for (int i = 1; i <= threadCount; ++i) {
            new Thread(() -> {
                try {
                    startSignal.await();
                    try {
                        Converts.onJackson().builder().buildGlobal();
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
        Assertions.assertEquals(1, passThread.get());
        restoreGlobalJacksonMapperToAllowReplaceAgain();
    }

    @SneakyThrows
    @Test
    void testCustomizeLocalAndGlobalJacksonConverts() {
        Assertions.assertTrue(Strings.containsBlank(Converts.onJackson()
                .builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .build()
                .getConverts()
                .toString(STUDENT_DO))
        );

        Assertions.assertFalse(Strings.containsBlank(Converts.onJackson().toString(STUDENT_DO)));

        Converts.onJackson()
                .builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .buildGlobal();
        Assertions.assertTrue(Strings.containsBlank(Converts.onJackson().toString(STUDENT_DO)));
        restoreGlobalJacksonMapperToAllowReplaceAgain();
    }

    private void restoreGlobalJacksonMapperToAllowReplaceAgain() {
        Reflects.setFieldValue(JacksonConverts.JacksonMapper.Builder.class, "allowToReplaceGlobalObjectMapper", Boolean.TRUE);
    }

}