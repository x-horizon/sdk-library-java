package org.horizon.library.java.cache.all.service;

import org.horizon.library.java.cache.all.CacheConfig;
import org.horizon.library.java.cache.all.CacheEvict;
import org.horizon.library.java.cache.all.CacheRead;
import org.horizon.library.java.cache.all.CacheWrite;
import org.horizon.library.java.cache.all.model.BookPO;
import org.horizon.library.java.cache.all.model.enums.CacheType;
import org.horizon.library.java.cache.all.test.CacheTestKeyGenerator;
import org.springframework.stereotype.Component;

@CacheConfig(namespaces = {"myCache2", "myCache3"}, cacheTypes = {CacheType.MAP, CacheType.CAFFEINE, CacheType.REDIS}, allowEmptyValue = true)
@Component
public class CacheContextWithCacheConfigBuildingService {

    // ========================= cache read =========================

    @CacheRead
    public BookPO getById1(BookPO bookPO) {
        return null;
    }

    @CacheRead(key = "#bookPO.author")
    public BookPO getById2(BookPO bookPO) {
        return null;
    }

    @CacheRead(key = "#bookPO.name")
    public BookPO getById3(BookPO bookPO) {
        return null;
    }

    @CacheRead(key = "#bookPO.id")
    public BookPO getById4(BookPO bookPO) {
        return null;
    }

    @CacheRead(namespaces = {"myCache4"}, cacheTypes = {CacheType.CAFFEINE, CacheType.MAP, CacheType.REDIS, CacheType.MAP, CacheType.MAP, CacheType.CAFFEINE, CacheType.MAP, CacheType.CAFFEINE, CacheType.REDIS, CacheType.CAFFEINE, CacheType.REDIS, CacheType.REDIS})
    public BookPO getById5(BookPO bookPO) {
        return null;
    }

    @CacheRead(namespaces = {"myCache4"}, cacheTypes = {CacheType.CAFFEINE, CacheType.MAP, CacheType.REDIS, CacheType.MAP, CacheType.MAP, CacheType.CAFFEINE, CacheType.MAP, CacheType.CAFFEINE, CacheType.REDIS, CacheType.CAFFEINE, CacheType.REDIS, CacheType.REDIS}, keyGenerator = CacheTestKeyGenerator.class)
    public BookPO getById6(BookPO bookPO) {
        return null;
    }

    @CacheRead(namespaces = {"myCache4"}, cacheTypes = {CacheType.CAFFEINE, CacheType.MAP, CacheType.REDIS, CacheType.MAP, CacheType.MAP, CacheType.CAFFEINE, CacheType.MAP, CacheType.CAFFEINE, CacheType.REDIS, CacheType.CAFFEINE, CacheType.REDIS, CacheType.REDIS}, key = "#bookPO.id")
    public BookPO getById7(BookPO bookPO) {
        return null;
    }

    // ========================= cache write =========================

    @CacheWrite
    public BookPO getById8(BookPO bookPO) {
        return null;
    }

    @CacheWrite(key = "#bookPO.author")
    public BookPO getById9(BookPO bookPO) {
        return null;
    }

    @CacheWrite(key = "#bookPO.name")
    public BookPO getById10(BookPO bookPO) {
        return null;
    }

    @CacheWrite(key = "#bookPO.id")
    public BookPO getById11(BookPO bookPO) {
        return null;
    }

    @CacheWrite(namespaces = {"myCache4"}, cacheTypes = {CacheType.CAFFEINE, CacheType.MAP, CacheType.REDIS, CacheType.MAP, CacheType.MAP, CacheType.CAFFEINE, CacheType.MAP, CacheType.CAFFEINE, CacheType.REDIS, CacheType.CAFFEINE, CacheType.REDIS, CacheType.REDIS})
    public BookPO getById12(BookPO bookPO) {
        return null;
    }

    @CacheWrite(namespaces = {"myCache4"}, cacheTypes = {CacheType.CAFFEINE, CacheType.MAP, CacheType.REDIS, CacheType.MAP, CacheType.MAP, CacheType.CAFFEINE, CacheType.MAP, CacheType.CAFFEINE, CacheType.REDIS, CacheType.CAFFEINE, CacheType.REDIS, CacheType.REDIS}, key = "#bookPO.id")
    public BookPO getById14(BookPO bookPO) {
        return null;
    }

    // ========================= cache evict =========================

    @CacheEvict
    public BookPO getById15(BookPO bookPO) {
        return null;
    }

    @CacheEvict(key = "#bookPO.author")
    public BookPO getById16(BookPO bookPO) {
        return null;
    }

    @CacheEvict(key = "#bookPO.name")
    public BookPO getById17(BookPO bookPO) {
        return null;
    }

    @CacheEvict(key = "#bookPO.id")
    public BookPO getById18(BookPO bookPO) {
        return null;
    }

    @CacheEvict(namespaces = {"myCache4"}, cacheTypes = {CacheType.CAFFEINE, CacheType.MAP, CacheType.REDIS, CacheType.MAP, CacheType.MAP, CacheType.CAFFEINE, CacheType.MAP, CacheType.CAFFEINE, CacheType.REDIS, CacheType.CAFFEINE, CacheType.REDIS, CacheType.REDIS})
    public BookPO getById19(BookPO bookPO) {
        return null;
    }

    @CacheEvict(namespaces = {"myCache4"}, cacheTypes = {CacheType.CAFFEINE, CacheType.MAP, CacheType.REDIS, CacheType.MAP, CacheType.MAP, CacheType.CAFFEINE, CacheType.MAP, CacheType.CAFFEINE, CacheType.REDIS, CacheType.CAFFEINE, CacheType.REDIS, CacheType.REDIS}, keyGenerator = CacheTestKeyGenerator.class)
    public BookPO getById20(BookPO bookPO) {
        return null;
    }

    @CacheEvict(namespaces = {"myCache4"}, cacheTypes = {CacheType.CAFFEINE, CacheType.MAP, CacheType.REDIS, CacheType.MAP, CacheType.MAP, CacheType.CAFFEINE, CacheType.MAP, CacheType.CAFFEINE, CacheType.REDIS, CacheType.CAFFEINE, CacheType.REDIS, CacheType.REDIS}, key = "#bookPO.id")
    public BookPO getById21(BookPO bookPO) {
        return null;
    }

    @CacheEvict(namespaces = {"myCache4"}, cacheTypes = {CacheType.CAFFEINE, CacheType.MAP, CacheType.REDIS, CacheType.MAP, CacheType.MAP, CacheType.CAFFEINE, CacheType.MAP, CacheType.CAFFEINE, CacheType.REDIS, CacheType.CAFFEINE, CacheType.REDIS, CacheType.REDIS}, key = "#bookPO.id", needEvictBeforeProceed = true, needEvictAllInNamespaces = true)
    public BookPO getById22(BookPO bookPO) {
        return null;
    }

}