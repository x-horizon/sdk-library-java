package cn.srd.itcp.sugar.cache.redisson;

import cn.srd.itcp.sugar.cache.redisson.core.RedissonCacheTemplate;
import cn.srd.itcp.sugar.cache.redisson.core.RedissonCaches;
import cn.srd.itcp.sugar.tool.core.time.DurationWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.support.NullValue;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedissonCacheTest {

    private static final String CACHE_NAMESPACE_NAME1 = "cache1";

    private static final String CACHE_NAMESPACE_NAME2 = "cache2";

    private static final String CACHE_NAME1 = CACHE_NAMESPACE_NAME1 + ":cache";

    private static final String CACHE_NAME2 = CACHE_NAMESPACE_NAME2 + ":cache";

    private static final Integer CACHE_NUMBER = 1;

    private static final String CACHE_STRING = "test";

    private static final Student CACHE_OBJECT1 = Student.builder().id(1).name("test1").build();

    private static final Student CACHE_OBJECT2 = Student.builder().id(2).name("test2").build();

    @Test
    public void testCache() {
        RedissonCacheTemplate cache = RedissonCaches.getInstance().withBucket();

        // =================== no handle NullValue ===================

        cache.set(CACHE_NAME1, CACHE_NUMBER);
        Integer result1 = cache.get(CACHE_NAME1, Integer.class);

        cache.set(CACHE_NAME1, CACHE_STRING);
        String result2 = cache.get(CACHE_NAME1, String.class);

        cache.set(CACHE_NAME1, CACHE_OBJECT1);
        Student result3 = cache.get(CACHE_NAME1, Student.class);

        long affectedNumber1 = cache.deleteByNamespace(CACHE_NAMESPACE_NAME1);

        boolean result4 = cache.setIfAbsent(CACHE_NAME1, CACHE_OBJECT1);
        boolean result6 = cache.setIfAbsent(CACHE_NAME1, CACHE_OBJECT2);

        long affectedNumber2 = cache.deleteByNamespace(CACHE_NAMESPACE_NAME1);

        boolean result8 = cache.setIfExists(CACHE_NAME1, CACHE_OBJECT2);
        cache.set(CACHE_NAME1, CACHE_OBJECT2);
        boolean result9 = cache.setIfExists(CACHE_NAME1, CACHE_OBJECT1);

        long affectedNumber3 = cache.deleteByNamespace(CACHE_NAMESPACE_NAME1);

        cache.set(CACHE_NAME1, CACHE_OBJECT1);
        cache.set(CACHE_NAME2, CACHE_OBJECT2);
        Student result10 = cache.get(CACHE_NAME1, Student.class);
        Student result11 = cache.get(CACHE_NAME2, Student.class);
        List<Student> result12 = cache.get(CACHE_NAME1, CACHE_NAME2);
        List<Student> result13 = cache.get(List.of(CACHE_NAME1, CACHE_NAME2));
        Map<String, Student> result14 = cache.getMap(CACHE_NAME1, CACHE_NAME2);
        Map<String, Student> result15 = cache.getMap(List.of(CACHE_NAME1, CACHE_NAME2));

        long affectedNumber4 = cache.deleteByNamespace(CACHE_NAMESPACE_NAME1, CACHE_NAMESPACE_NAME2);

        Student result16 = cache.getAndSet(CACHE_NAME1, CACHE_OBJECT1, Student.class);
        Student result17 = cache.getAndSet(CACHE_NAME1, CACHE_OBJECT2, Student.class);
        Student result18 = cache.get(CACHE_NAME1, Student.class);
        Student result19 = cache.getAndDelete(CACHE_NAME1, Student.class);
        Student result20 = cache.getAndDelete(CACHE_NAME1, Student.class);

        long affectedNumber5 = cache.deleteByNamespace(CACHE_NAMESPACE_NAME1);

        cache.set(CACHE_NAME1, CACHE_OBJECT2);
        boolean result21 = cache.compareAndSet(CACHE_NAME1, CACHE_OBJECT1, CACHE_OBJECT2);
        boolean result22 = cache.compareAndSet(CACHE_NAME1, CACHE_OBJECT2, CACHE_OBJECT1);

        long affectedNumber6 = cache.deleteByNamespace(CACHE_NAMESPACE_NAME1);

        cache.set(CACHE_NAME1, CACHE_OBJECT1);
        cache.set(CACHE_NAME2, CACHE_OBJECT2);
        List<Student> result23 = cache.getByNamespace(CACHE_NAMESPACE_NAME1, CACHE_NAMESPACE_NAME2);
        List<Student> result24 = cache.getByPattern(CACHE_NAMESPACE_NAME1 + ":*", CACHE_NAMESPACE_NAME2 + ":*");

        long affectedNumber7 = cache.deleteByPattern(CACHE_NAMESPACE_NAME1 + ":*", CACHE_NAMESPACE_NAME2 + ":*");

        cache.set(CACHE_NAME1, CACHE_OBJECT2, 10, TimeUnit.SECONDS);
        DurationWrapper result25 = cache.getExpirationTime(CACHE_NAME1);
        DurationWrapper result26 = cache.getTimeToLive(CACHE_NAME1);

        long affectedNumber8 = cache.deleteByNamespace(CACHE_NAMESPACE_NAME1);

        cache.set(CACHE_NAME1, CACHE_OBJECT2, Duration.ofSeconds(10));
        DurationWrapper result27 = cache.getExpirationTime(CACHE_NAME1);
        DurationWrapper result28 = cache.getTimeToLive(CACHE_NAME1);

        long affectedNumber9 = cache.deleteByNamespace(CACHE_NAMESPACE_NAME1);

        boolean result29 = cache.setIfAbsent(CACHE_NAME1, CACHE_OBJECT2, 10, TimeUnit.SECONDS);
        boolean result30 = cache.setIfAbsent(CACHE_NAME1, CACHE_OBJECT1, 10, TimeUnit.SECONDS);
        boolean result31 = cache.setIfAbsent(CACHE_NAME2, CACHE_OBJECT1, Duration.ofSeconds(10));
        boolean result32 = cache.setIfAbsent(CACHE_NAME2, CACHE_OBJECT2, Duration.ofSeconds(10));
        DurationWrapper result33 = cache.getExpirationTime(CACHE_NAME1);
        DurationWrapper result34 = cache.getExpirationTime(CACHE_NAME2);
        DurationWrapper result35 = cache.getTimeToLive(CACHE_NAME1);
        DurationWrapper result36 = cache.getTimeToLive(CACHE_NAME2);

        long affectedNumber10 = cache.deleteByNamespace(CACHE_NAMESPACE_NAME1, CACHE_NAMESPACE_NAME2);

        cache.set(CACHE_NAME1, CACHE_OBJECT2);
        boolean result37 = cache.setIfExists(CACHE_NAME1, CACHE_OBJECT1, 10, TimeUnit.SECONDS);
        boolean result38 = cache.setIfExists(CACHE_NAME2, CACHE_OBJECT2, 10, TimeUnit.SECONDS);
        DurationWrapper result39 = cache.getExpirationTime(CACHE_NAME1);
        DurationWrapper result40 = cache.getExpirationTime(CACHE_NAME2);
        DurationWrapper result41 = cache.getTimeToLive(CACHE_NAME1);
        DurationWrapper result42 = cache.getTimeToLive(CACHE_NAME2);

        long affectedNumber11 = cache.deleteByNamespace(CACHE_NAMESPACE_NAME1, CACHE_NAMESPACE_NAME2);

        cache.set(CACHE_NAME1, CACHE_OBJECT2);
        boolean result43 = cache.setIfExists(CACHE_NAME1, CACHE_OBJECT1, Duration.ofSeconds(10));
        boolean result44 = cache.setIfExists(CACHE_NAME2, CACHE_OBJECT2, Duration.ofSeconds(10));
        DurationWrapper result45 = cache.getExpirationTime(CACHE_NAME1);
        DurationWrapper result46 = cache.getExpirationTime(CACHE_NAME2);
        DurationWrapper result47 = cache.getTimeToLive(CACHE_NAME1);
        DurationWrapper result48 = cache.getTimeToLive(CACHE_NAME2);

        long affectedNumber12 = cache.deleteByNamespace(CACHE_NAMESPACE_NAME1, CACHE_NAMESPACE_NAME2);

        cache.set(CACHE_NAME1, CACHE_OBJECT2);
        Student result49 = cache.getAndSet(CACHE_NAME1, CACHE_OBJECT1, Student.class, Duration.ofSeconds(10));
        DurationWrapper result50 = cache.getExpirationTime(CACHE_NAME1);
        DurationWrapper result51 = cache.getTimeToLive(CACHE_NAME1);

        long affectedNumber13 = cache.deleteByNamespace(CACHE_NAMESPACE_NAME1);

        cache.set(CACHE_NAME1, CACHE_OBJECT2);
        Student result52 = cache.getAndSet(CACHE_NAME1, CACHE_OBJECT1, Student.class, 10, TimeUnit.SECONDS);
        DurationWrapper result53 = cache.getExpirationTime(CACHE_NAME1);
        DurationWrapper result54 = cache.getTimeToLive(CACHE_NAME1);

        long affectedNumber14 = cache.deleteByNamespace(CACHE_NAMESPACE_NAME1);

        // =================== handle NullValue ===================

        cache.set(CACHE_NAME1, CACHE_NUMBER);
        Integer result55 = cache.getWithoutNullValue(CACHE_NAME1, Integer.class);

        cache.set(CACHE_NAME1, CACHE_STRING);
        String result56 = cache.getWithoutNullValue(CACHE_NAME1, String.class);

        cache.set(CACHE_NAME1, CACHE_OBJECT1);
        Student result57 = cache.getWithoutNullValue(CACHE_NAME1, Student.class);

        long affectedNumber15 = cache.deleteByNamespace(CACHE_NAMESPACE_NAME1);

        cache.set(CACHE_NAME1, NullValue.INSTANCE);
        cache.set(CACHE_NAME2, CACHE_OBJECT2);
        Student result58 = cache.getWithoutNullValue(CACHE_NAME1, Student.class);
        Student result59 = cache.getWithoutNullValue(CACHE_NAME2, Student.class);
        List<Student> result60 = cache.getWithoutNullValue(CACHE_NAME1, CACHE_NAME2);
        List<Student> result61 = cache.getWithoutNullValue(List.of(CACHE_NAME1, CACHE_NAME2));
        Map<String, Student> result62 = cache.getMapWithoutNullValue(CACHE_NAME1, CACHE_NAME2);
        Map<String, Student> result63 = cache.getMapWithoutNullValue(List.of(CACHE_NAME1, CACHE_NAME2));

        long affectedNumber16 = cache.deleteByNamespace(CACHE_NAMESPACE_NAME1, CACHE_NAMESPACE_NAME2);

        Student result64 = cache.getAndSetWithoutNullValue(CACHE_NAME1, CACHE_OBJECT1, Student.class);
        Student result65 = cache.getAndSetWithoutNullValue(CACHE_NAME1, CACHE_OBJECT2, Student.class);
        Student result66 = cache.getWithoutNullValue(CACHE_NAME1, Student.class);
        Student result67 = cache.getAndDeleteWithoutNullValue(CACHE_NAME1, Student.class);
        Student result68 = cache.getAndDeleteWithoutNullValue(CACHE_NAME1, Student.class);

        long affectedNumber17 = cache.deleteByNamespace(CACHE_NAMESPACE_NAME1);

        cache.set(CACHE_NAME1, NullValue.INSTANCE);
        cache.set(CACHE_NAME2, CACHE_OBJECT2);
        List<Student> result69 = cache.getByNamespaceWithoutNullValue(CACHE_NAMESPACE_NAME1, CACHE_NAMESPACE_NAME2);
        List<Student> result70 = cache.getByPatternWithoutNullValue(CACHE_NAMESPACE_NAME1 + ":*", CACHE_NAMESPACE_NAME2 + ":*");

        long affectedNumber18 = cache.deleteByPattern(CACHE_NAMESPACE_NAME1 + ":*", CACHE_NAMESPACE_NAME2 + ":*");

        cache.set(CACHE_NAME1, CACHE_OBJECT2);
        Student result71 = cache.getAndSetWithoutNullValue(CACHE_NAME1, CACHE_OBJECT1, Student.class, Duration.ofSeconds(10));
        DurationWrapper result72 = cache.getExpirationTime(CACHE_NAME1);
        DurationWrapper result73 = cache.getTimeToLive(CACHE_NAME1);

        long affectedNumber19 = cache.deleteByNamespace(CACHE_NAMESPACE_NAME1);

        cache.set(CACHE_NAME1, CACHE_OBJECT2);
        Student result74 = cache.getAndSetWithoutNullValue(CACHE_NAME1, CACHE_OBJECT1, Student.class, 10, TimeUnit.SECONDS);
        DurationWrapper result75 = cache.getExpirationTime(CACHE_NAME1);
        DurationWrapper result76 = cache.getTimeToLive(CACHE_NAME1);

        long affectedNumber20 = cache.deleteByNamespace(CACHE_NAMESPACE_NAME1);
    }

}

