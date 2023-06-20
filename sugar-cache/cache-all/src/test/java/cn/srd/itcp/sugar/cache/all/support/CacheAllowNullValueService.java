package cn.srd.itcp.sugar.cache.all.support;

import cn.srd.itcp.sugar.cache.all.core.*;
import cn.srd.itcp.sugar.cache.all.support.manager.CacheMode;
import cn.srd.itcp.sugar.cache.all.support.manager.CacheType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@CacheConfig(namespaces = {"myCache7", "myCache8"}, cacheTypes = {CacheType.MAP, CacheType.CAFFEINE, CacheType.MAP, CacheType.MAP, CacheType.CAFFEINE, CacheType.MAP, CacheType.REDIS}, allowNullValue = true)
@Component
public class CacheAllowNullValueService {

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

    @CacheRead(namespaces = "${project.cache.test-cache-key}", key = "#id")
    public BookPO getById2(Long id) {
        return BOOK_CACHE.get(id);
    }

    @CacheRead(key = "#id")
    public BookPO getNull(Long id) {
        return null;
    }

    @CacheReadAll(key = "#this.id")
    public List<BookPO> getAll() {
        return BOOK_CACHE.values().stream().toList();
    }

    @CacheReadAll(key = "#this.id")
    public List<BookPO> getAll2() {
        return new ArrayList<>();
    }

    @CacheWrite(key = "#this.id")
    public BookPO save(BookPO bookPO) {
        BOOK_CACHE.put(bookPO.getId(), bookPO);
        return bookPO;
    }

    @CacheWrite(cacheMode = CacheMode.READ_WRITE, key = "#this.id")
    public BookPO save2(BookPO bookPO) {
        BOOK_CACHE.put(bookPO.getId(), bookPO);
        return bookPO;
    }

    @CacheWrite(key = "#this.id")
    public BookPO saveNull(BookPO bookPO) {
        return null;
    }

    @CacheEvict(key = "#id")
    public void deleteById(Long id) {
        BOOK_CACHE.remove(id);
    }

    @CacheEvict(key = "#ids")
    public void deleteByIds(List<Long> ids) {
        ids.forEach(BOOK_CACHE::remove);
    }

    @CacheEvict(key = "#id", needEvictAllInNamespaces = true)
    public void deleteAll1(Long id) {
        BOOK_CACHE.remove(id);
    }

    @CacheEvict(needEvictAllInNamespaces = true)
    public void deleteAll2(Long id) {
        BOOK_CACHE.remove(id);
    }

    @CacheEvict(key = "#id", needEvictBeforeProceed = true)
    public void deleteBeforeProceedById(Long id) {
        BOOK_CACHE.remove(id);
    }

    @CacheEvict(key = "#id", needEvictBeforeProceed = true, needEvictAllInNamespaces = true)
    public void deleteBeforeProceedAll(Long id) {
        BOOK_CACHE.remove(id);
    }

    @Caching(
            read = {
                    @CacheRead(namespaces = "myCache10", key = "#bookPO.id"),
                    @CacheRead(namespaces = "myCache11", key = "#bookPO.id")
            },
            write = {
                    @CacheWrite(namespaces = {"myCache12"}, key = "#this.id"),
                    @CacheWrite(namespaces = {"myCache12", "myCache13"}, key = "#this.name")
            },
            evict = {
                    @CacheEvict(namespaces = {"myCache12"}, key = "#bookPO.id"),
                    @CacheEvict(namespaces = {"myCache13"}, needEvictAllInNamespaces = true),
                    @CacheEvict(namespaces = {"myCache12"}, needEvictAllInNamespaces = true),
                    @CacheEvict(namespaces = {"myCache10"}, needEvictAllInNamespaces = true),
                    @CacheEvict(namespaces = {"myCache11"}, needEvictAllInNamespaces = true)
            }
    )
    public BookPO multi(BookPO bookPO) {
        return bookPO;
    }

}
