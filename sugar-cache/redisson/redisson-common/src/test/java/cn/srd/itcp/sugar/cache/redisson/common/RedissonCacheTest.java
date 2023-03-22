package cn.srd.itcp.sugar.cache.redisson.common;

import cn.srd.itcp.sugar.cache.redisson.common.core.cache.RedissonCacheHandler;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedissonCacheTest {

    private static final String CACHE_NAME = "cache";

    private static final Integer CACHE_NUMBER = 1;

    private static final String CACHE_STRING = "test";

    private static final Student CACHE_OBJECT = Student.builder().id(1).name("test").build();

    @Test
    public void redissonCacheTest() {
        RedissonCacheHandler.BUCKET.set(CACHE_NAME, CACHE_NUMBER);
        RedissonCacheHandler.BUCKET.get(CACHE_NAME, Integer.class);

        RedissonCacheHandler.BUCKET.set(CACHE_NAME, CACHE_STRING);
        RedissonCacheHandler.BUCKET.get(CACHE_NAME, String.class);

        RedissonCacheHandler.BUCKET.set(CACHE_NAME, CACHE_OBJECT);
        RedissonCacheHandler.BUCKET.get(CACHE_NAME, Student.class);

        // Assert.assertEquals(multiThreadOperate(() -> RedissonFairLockHandler.getInstance().lock(criticalSection(), LOCK_NAME)), 0);
    }

}

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
class Student {
    private Integer id;
    private String name;
}
