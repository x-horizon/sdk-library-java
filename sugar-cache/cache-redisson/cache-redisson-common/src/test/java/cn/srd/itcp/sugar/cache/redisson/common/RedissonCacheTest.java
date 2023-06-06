package cn.srd.itcp.sugar.cache.redisson.common;

import cn.srd.itcp.sugar.cache.redisson.common.core.RedissonCaches;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedissonCacheTest {

    private static final String CACHE_NAME = "cache";

    private static final Integer CACHE_NUMBER = 1;

    private static final String CACHE_STRING = "test";

    private static final Student CACHE_OBJECT = Student.builder().id(1).name("test").build();

    @Test
    public void testCache() {
        RedissonCaches.getInstance().withBucket().set(CACHE_NAME, CACHE_NUMBER);
        Integer result1 = RedissonCaches.getInstance().withBucket().get(CACHE_NAME, Integer.class);

        RedissonCaches.getInstance().withBucket().set(CACHE_NAME, CACHE_STRING);
        String result2 = RedissonCaches.getInstance().withBucket().get(CACHE_NAME, String.class);

        RedissonCaches.getInstance().withBucket().set(CACHE_NAME, CACHE_OBJECT);
        Student result3 = RedissonCaches.getInstance().withBucket().get(CACHE_NAME, Student.class);

        RedissonCaches.getInstance().withBucket().set(CACHE_NAME, CACHE_OBJECT, "10s");
        long result4 = RedissonCaches.getInstance().withBucket().getExpirationTime(CACHE_NAME);
        long result5 = RedissonCaches.getInstance().withBucket().getTimeToLive(CACHE_NAME);

        RedissonCaches.getInstance().withBucket().set(CACHE_NAME, CACHE_OBJECT, 10, TimeUnit.SECONDS);
        long result6 = RedissonCaches.getInstance().withBucket().getExpirationTime(CACHE_NAME);
        long result7 = RedissonCaches.getInstance().withBucket().getTimeToLive(CACHE_NAME);

        RedissonCaches.getInstance().withBucket().set(CACHE_NAME, CACHE_OBJECT, Duration.ofSeconds(10));
        long result8 = RedissonCaches.getInstance().withBucket().getExpirationTime(CACHE_NAME);
        long result9 = RedissonCaches.getInstance().withBucket().getTimeToLive(CACHE_NAME);
    }

}

