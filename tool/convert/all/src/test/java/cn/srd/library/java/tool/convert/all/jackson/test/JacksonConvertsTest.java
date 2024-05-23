// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.all.jackson.test;

import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.convert.all.jackson.model.GradeDO;
import cn.srd.library.java.tool.convert.all.jackson.model.StudentDO;
import cn.srd.library.java.tool.convert.jackson.JacksonConverts;
import cn.srd.library.java.tool.convert.jackson.Jacksons;
import cn.srd.library.java.tool.lang.file.Files;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import cn.srd.library.java.tool.lang.text.Strings;
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
        byte[] gradeDOJson = Converts.withJackson().toBytes(GRADE_DO);
        Converts.withJackson().toBean(gradeDOJson, GradeDO.class);
        byte[] gradeDOsJsonArray = Converts.withJackson().toBytes(GRADE_DOS);
        Converts.withJackson().toBeans(gradeDOsJsonArray, GradeDO.class);
    }

    @SneakyThrows
    @Test
    void testJacksonConverts() {
        String gradeDOJson = Converts.withJackson().toString(GRADE_DO);
        String gradeDOsJsonArray = Converts.withJackson().toString(GRADE_DOS);
        String stringMappingGradeDOMapJson = Converts.withJackson().toString(STRING_MAPPING_GRADE_DO_MAP);
        String stringMappingGradeDOsMapJson = Converts.withJackson().toString(STRING_MAPPING_GRADE_DOS_MAP);
        String studentDOMappingGradeDOMapJson = Converts.withJackson().toString(STUDENT_DO_MAPPING_GRADE_DO_MAP);
        String studentDOsMappingGradeDOsMapJson = Converts.withJackson().toString(STUDENT_DOS_MAPPING_GRADE_DOS_MAP);
        Assertions.assertTrue(Nil.isNotBlank(gradeDOJson));
        Assertions.assertTrue(Nil.isNotBlank(gradeDOsJsonArray));
        Assertions.assertTrue(Nil.isNotBlank(stringMappingGradeDOMapJson));
        Assertions.assertTrue(Nil.isNotBlank(stringMappingGradeDOsMapJson));
        Assertions.assertTrue(Nil.isNotBlank(studentDOMappingGradeDOMapJson));
        Assertions.assertTrue(Nil.isNotBlank(studentDOsMappingGradeDOsMapJson));

        Assertions.assertTrue(Nil.isNotBlank(Converts.withJackson().toJsonNode(gradeDOJson).get("name").asText()));

        Assertions.assertNotNull(Converts.withJackson().toAnything(gradeDOJson, Jacksons.<GradeDO>newTypeReference()));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.withJackson().toAnything(gradeDOsJsonArray, Jacksons.<List<GradeDO>>newTypeReference())));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.withJackson().toAnything(stringMappingGradeDOMapJson, Jacksons.<Map<String, GradeDO>>newTypeReference())));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.withJackson().toAnything(stringMappingGradeDOsMapJson, Jacksons.<Map<String, List<GradeDO>>>newTypeReference())));

        Assertions.assertNotNull(Converts.withJackson().toBean(gradeDOJson, GradeDO.class));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.withJackson().toBeans(gradeDOsJsonArray, GradeDO.class)));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.withJackson().toMap(stringMappingGradeDOMapJson, GradeDO.class)));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.withJackson().toMaps(stringMappingGradeDOsMapJson, GradeDO.class)));

        Assertions.assertThrowsExactly(ConstraintViolationException.class, () -> Converts.withJackson().toBean(gradeDOJson, GradeDO.class, true));
        Assertions.assertThrowsExactly(ConstraintViolationException.class, () -> Converts.withJackson().toBeans(gradeDOsJsonArray, GradeDO.class, true));
        Assertions.assertThrowsExactly(ConstraintViolationException.class, () -> Converts.withJackson().toMap(stringMappingGradeDOMapJson, GradeDO.class, true));
        Assertions.assertThrowsExactly(ConstraintViolationException.class, () -> Converts.withJackson().toMaps(stringMappingGradeDOsMapJson, GradeDO.class, true));

        String path = "test.json";
        File file = new File(path);
        URL url = URI.create("file:" + path).toURL();

        Converts.withJackson().writeToFile(file, GRADE_DO);
        Assertions.assertTrue(Files.exist(file));
        Assertions.assertNotNull(Converts.withJackson().toBean(file, GradeDO.class));
        Assertions.assertNotNull(Converts.withJackson().toBean(url, GradeDO.class));
        Assertions.assertNotNull(Converts.withJackson().toAnything(file, Jacksons.<GradeDO>newTypeReference()));
        Assertions.assertNotNull(Converts.withJackson().toAnything(url, Jacksons.<GradeDO>newTypeReference()));
        Files.delete(file);

        Converts.withJackson().writeToFile(file, GRADE_DOS);
        Assertions.assertTrue(Files.exist(file));
        Assertions.assertNotNull(Converts.withJackson().toBeans(file, GradeDO.class));
        Assertions.assertNotNull(Converts.withJackson().toBeans(url, GradeDO.class));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.withJackson().toAnything(file, Jacksons.<List<GradeDO>>newTypeReference())));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.withJackson().toAnything(url, Jacksons.<List<GradeDO>>newTypeReference())));
        Files.delete(file);

        Converts.withJackson().writeToFile(file, STRING_MAPPING_GRADE_DO_MAP);
        Assertions.assertTrue(Files.exist(file));
        Assertions.assertNotNull(Converts.withJackson().toMap(file, GradeDO.class));
        Assertions.assertNotNull(Converts.withJackson().toMap(url, GradeDO.class));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.withJackson().toAnything(file, Jacksons.<Map<String, GradeDO>>newTypeReference())));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.withJackson().toAnything(url, Jacksons.<Map<String, GradeDO>>newTypeReference())));
        Files.delete(file);

        Converts.withJackson().writeToFile(file, STRING_MAPPING_GRADE_DOS_MAP);
        Assertions.assertTrue(Files.exist(file));
        Assertions.assertNotNull(Converts.withJackson().toMaps(file, GradeDO.class));
        Assertions.assertNotNull(Converts.withJackson().toMaps(url, GradeDO.class));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.withJackson().toAnything(file, Jacksons.<Map<String, List<GradeDO>>>newTypeReference())));
        Assertions.assertTrue(Nil.isNotEmpty(Converts.withJackson().toAnything(url, Jacksons.<Map<String, List<GradeDO>>>newTypeReference())));
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
        Assertions.assertEquals(1, passThread.get());
        restoreGlobalJacksonMapperToAllowReplaceAgain();
    }

    @SneakyThrows
    @Test
    void testCustomizeLocalAndGlobalJacksonConverts() {
        Assertions.assertTrue(Strings.containsBlank(Converts.withJackson()
                .builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .build()
                .getConverts()
                .toString(STUDENT_DO))
        );

        Assertions.assertFalse(Strings.containsBlank(Converts.withJackson().toString(STUDENT_DO)));

        Converts.withJackson()
                .builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .buildGlobal();
        Assertions.assertTrue(Strings.containsBlank(Converts.withJackson().toString(STUDENT_DO)));
        restoreGlobalJacksonMapperToAllowReplaceAgain();
    }

    private void restoreGlobalJacksonMapperToAllowReplaceAgain() {
        Reflects.setFieldValue(JacksonConverts.JacksonMapper.Builder.class, "allowToReplaceGlobalObjectMapper", Boolean.TRUE);
    }

}