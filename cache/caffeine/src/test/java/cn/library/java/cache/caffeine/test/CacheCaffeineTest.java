package cn.library.java.cache.caffeine.test;

import cn.library.java.cache.caffeine.CaffeineCache;
import cn.library.java.cache.caffeine.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.support.NullValue;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CacheCaffeineTest {

    private static final String CACHE_NAME1 = "cache1";

    private static final String CACHE_NAME2 = "cache2";

    private static final Integer CACHE_NUMBER = 1;

    private static final String CACHE_STRING = "test";

    private static final Student CACHE_OBJECT1 = Student.builder().id(1).name("test1").build();

    private static final Student CACHE_OBJECT2 = Student.builder().id(2).name("test2").build();

    @Test
    void testCache() {
        CaffeineCache<String> cache = CaffeineCache.newInstance();

        // =================== no handle NullValue ===================

        cache.set(CACHE_NAME1, CACHE_NUMBER);
        Integer result1 = cache.get(CACHE_NAME1, Integer.class);

        cache.set(CACHE_NAME1, CACHE_STRING);
        String result2 = cache.get(CACHE_NAME1, String.class);

        cache.set(CACHE_NAME1, CACHE_OBJECT1);
        Student result3 = cache.get(CACHE_NAME1, Student.class);

        cache.deleteAll();

        boolean result4 = cache.setIfAbsent(CACHE_NAME1, CACHE_OBJECT1);
        boolean result6 = cache.setIfAbsent(CACHE_NAME1, CACHE_OBJECT2);

        cache.deleteAll();

        boolean result8 = cache.setIfExists(CACHE_NAME1, CACHE_OBJECT2);
        cache.set(CACHE_NAME1, CACHE_OBJECT2);
        boolean result9 = cache.setIfExists(CACHE_NAME1, CACHE_OBJECT1);

        cache.deleteAll();

        cache.set(CACHE_NAME1, CACHE_OBJECT1);
        cache.set(CACHE_NAME2, CACHE_OBJECT2);
        Student result10 = cache.get(CACHE_NAME1, Student.class);
        Student result11 = cache.get(CACHE_NAME2, Student.class);
        List<Student> result12 = cache.get(CACHE_NAME1, CACHE_NAME2);
        List<Student> result13 = cache.get(List.of(CACHE_NAME1, CACHE_NAME2));
        Map<String, Student> result14 = cache.getMap(CACHE_NAME1, CACHE_NAME2);
        Map<String, Student> result15 = cache.getMap(List.of(CACHE_NAME1, CACHE_NAME2));

        cache.deleteAll();

        Student result16 = cache.getAndSet(CACHE_NAME1, CACHE_OBJECT1, Student.class);
        Student result17 = cache.getAndSet(CACHE_NAME1, CACHE_OBJECT2, Student.class);
        Student result18 = cache.get(CACHE_NAME1, Student.class);
        Student result19 = cache.getAndDelete(CACHE_NAME1, Student.class);
        Student result20 = cache.getAndDelete(CACHE_NAME1, Student.class);

        cache.deleteAll();

        // =================== handle NullValue ===================

        cache.set(CACHE_NAME1, CACHE_NUMBER);
        Integer result21 = cache.getWithoutNullValue(CACHE_NAME1, Integer.class);

        cache.set(CACHE_NAME1, CACHE_STRING);
        String result22 = cache.getWithoutNullValue(CACHE_NAME1, String.class);

        cache.set(CACHE_NAME1, CACHE_OBJECT1);
        Student result23 = cache.getWithoutNullValue(CACHE_NAME1, Student.class);

        cache.deleteAll();

        cache.set(CACHE_NAME1, NullValue.INSTANCE);
        cache.set(CACHE_NAME2, CACHE_OBJECT2);
        Student result24 = cache.getWithoutNullValue(CACHE_NAME1, Student.class);
        Student result25 = cache.getWithoutNullValue(CACHE_NAME2, Student.class);
        List<Student> result26 = cache.getWithoutNullValue(CACHE_NAME1, CACHE_NAME2);
        List<Student> result27 = cache.getWithoutNullValue(List.of(CACHE_NAME1, CACHE_NAME2));
        Map<String, Student> result28 = cache.getMapWithoutNullValue(CACHE_NAME1, CACHE_NAME2);
        Map<String, Student> result29 = cache.getMapWithoutNullValue(List.of(CACHE_NAME1, CACHE_NAME2));

        cache.deleteAll();

        Student result30 = cache.getAndSetWithoutNullValue(CACHE_NAME1, CACHE_OBJECT1, Student.class);
        Student result31 = cache.getAndSetWithoutNullValue(CACHE_NAME1, CACHE_OBJECT2, Student.class);
        Student result32 = cache.getWithoutNullValue(CACHE_NAME1, Student.class);
        Student result33 = cache.getAndDeleteWithoutNullValue(CACHE_NAME1, Student.class);
        Student result34 = cache.getAndDeleteWithoutNullValue(CACHE_NAME1, Student.class);

        cache.deleteAll();
    }

}