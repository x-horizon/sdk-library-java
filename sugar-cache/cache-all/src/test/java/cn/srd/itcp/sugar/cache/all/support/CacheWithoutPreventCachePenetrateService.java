package cn.srd.itcp.sugar.cache.all.support;

import cn.srd.itcp.sugar.cache.all.core.CacheConfig;
import cn.srd.itcp.sugar.cache.all.core.CacheEvict;
import cn.srd.itcp.sugar.cache.all.core.CacheRead;
import cn.srd.itcp.sugar.cache.all.core.CacheWrite;
import cn.srd.itcp.sugar.cache.all.support.manager.CacheType;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@CacheConfig(namespaces = {"myCache5", "myCache6"}, cacheTypes = {CacheType.MAP, CacheType.CAFFEINE, CacheType.MAP, CacheType.MAP, CacheType.CAFFEINE, CacheType.MAP, CacheType.REDIS})
@Component
public class CacheWithoutPreventCachePenetrateService {

    private static final Map<Long, BookPO> BOOK_CACHE = new ConcurrentHashMap<>(Map.of(
            1L, BookPO.build(1L),
            2L, BookPO.build(2L),
            3L, BookPO.build(3L),
            4L, BookPO.build(4L),
            5L, BookPO.build(5L),
            6L, BookPO.build(6L)
    ));

    @CacheRead(key = "#id")
    public BookPO getById(Long id) {
        return BOOK_CACHE.get(id);
    }

    @CacheRead(key = "#id")
    public BookPO getNull(Long id) {
        return null;
    }

    @CacheWrite(key = "#bookPO.id")
    public BookPO save(BookPO bookPO) {
        BOOK_CACHE.put(bookPO.getId(), bookPO);
        return bookPO;
    }

    @CacheWrite(key = "#bookPO.id")
    public BookPO saveNull(BookPO bookPO) {
        return null;
    }

    @CacheEvict(key = "#id")
    public BookPO deleteById(Long id) {
        return BOOK_CACHE.remove(id);
    }

    @CacheEvict(key = "#id", needEvictAllInNamespaces = true)
    public BookPO deleteAll1(Long id) {
        return BOOK_CACHE.remove(id);
    }

    @CacheEvict(needEvictAllInNamespaces = true)
    public BookPO deleteAll2(Long id) {
        return BOOK_CACHE.remove(id);
    }

    @CacheEvict(key = "#id", needEvictBeforeProceed = true)
    public BookPO deleteBeforeProceedById(Long id) {
        return BOOK_CACHE.remove(id);
    }

    @CacheEvict(key = "#id", needEvictBeforeProceed = true, needEvictAllInNamespaces = true)
    public BookPO deleteBeforeProceedAll(Long id) {
        return BOOK_CACHE.remove(id);
    }

}
