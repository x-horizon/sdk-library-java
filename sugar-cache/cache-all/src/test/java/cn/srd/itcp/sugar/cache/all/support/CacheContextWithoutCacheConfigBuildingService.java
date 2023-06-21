package cn.srd.itcp.sugar.cache.all.support;

import cn.srd.itcp.sugar.cache.all.core.CacheEvict;
import cn.srd.itcp.sugar.cache.all.core.CacheRead;
import cn.srd.itcp.sugar.cache.all.core.CacheWrite;
import cn.srd.itcp.sugar.cache.all.support.manager.CacheType;
import org.springframework.stereotype.Component;

@Component
public class CacheContextWithoutCacheConfigBuildingService {

    // ========================= cache read =========================

    @CacheRead
    public BookPO getById1(BookPO bookPO) {
        return null;
    }

    @CacheRead(namespaces = {"myCache1"})
    public BookPO getById2(BookPO bookPO) {
        return null;
    }

    @CacheRead(namespaces = {"myCache1"}, cacheTypes = CacheType.MAP, keyGenerator = CacheTestKeyGenerator.class)
    public BookPO getById3(BookPO bookPO) {
        return null;
    }

    @CacheRead(namespaces = {"myCache1"}, cacheTypes = CacheType.MAP, key = "#bookPO.author")
    public BookPO getById4(BookPO bookPO) {
        return null;
    }

    @CacheRead(namespaces = {"myCache1"}, cacheTypes = CacheType.MAP, key = "#bookPO.name")
    public BookPO getById5(BookPO bookPO) {
        return null;
    }

    @CacheRead(namespaces = {"myCache1"}, cacheTypes = CacheType.MAP, key = "#bookPO.id")
    public BookPO getById6(BookPO bookPO) {
        return null;
    }

    @CacheRead(namespaces = {"myCache1"}, cacheTypes = CacheType.MAP, key = "1")
    public BookPO getById7(BookPO bookPO) {
        return null;
    }

    @CacheRead(namespaces = {"myCache1"}, cacheTypes = CacheType.MAP, key = "1", allowEmptyValue = true)
    public BookPO getById8(BookPO bookPO) {
        return null;
    }

    // ========================= cache write =========================

    @CacheWrite
    public BookPO getById9(BookPO bookPO) {
        return null;
    }

    @CacheWrite(namespaces = {"myCache1"})
    public BookPO getById10(BookPO bookPO) {
        return null;
    }

    @CacheWrite(namespaces = {"myCache1"}, cacheTypes = CacheType.MAP, key = "#bookPO.author")
    public BookPO getById12(BookPO bookPO) {
        return null;
    }

    @CacheWrite(namespaces = {"myCache1"}, cacheTypes = CacheType.MAP, key = "#bookPO.name")
    public BookPO getById13(BookPO bookPO) {
        return null;
    }

    @CacheWrite(namespaces = {"myCache1"}, cacheTypes = CacheType.MAP, key = "#bookPO.id")
    public BookPO getById14(BookPO bookPO) {
        return null;
    }

    @CacheWrite(namespaces = {"myCache1"}, cacheTypes = CacheType.MAP, key = "1")
    public BookPO getById15(BookPO bookPO) {
        return null;
    }

    @CacheWrite(namespaces = {"myCache1"}, cacheTypes = CacheType.MAP, key = "1", allowEmptyValue = true)
    public BookPO getById16(BookPO bookPO) {
        return null;
    }

    // ========================= cache evict =========================

    @CacheEvict
    public BookPO getById17(BookPO bookPO) {
        return null;
    }

    @CacheEvict(namespaces = {"myCache1"})
    public BookPO getById18(BookPO bookPO) {
        return null;
    }

    @CacheEvict(namespaces = {"myCache1"}, cacheTypes = CacheType.MAP, keyGenerator = CacheTestKeyGenerator.class)
    public BookPO getById19(BookPO bookPO) {
        return null;
    }

    @CacheEvict(namespaces = {"myCache1"}, cacheTypes = CacheType.MAP, key = "#bookPO.author")
    public BookPO getById20(BookPO bookPO) {
        return null;
    }

    @CacheEvict(namespaces = {"myCache1"}, cacheTypes = CacheType.MAP, key = "#bookPO.name")
    public BookPO getById21(BookPO bookPO) {
        return null;
    }

    @CacheEvict(namespaces = {"myCache1"}, cacheTypes = CacheType.MAP, key = "#bookPO.id")
    public BookPO getById22(BookPO bookPO) {
        return null;
    }

    @CacheEvict(namespaces = {"myCache1"}, cacheTypes = CacheType.MAP, key = "1")
    public BookPO getById23(BookPO bookPO) {
        return null;
    }

    @CacheEvict(namespaces = {"myCache1"}, cacheTypes = CacheType.MAP, key = "1", allowEmptyValue = true)
    public BookPO getById24(BookPO bookPO) {
        return null;
    }

    @CacheEvict(namespaces = {"myCache1"}, cacheTypes = CacheType.MAP, key = "1", allowEmptyValue = true, needEvictBeforeProceed = true)
    public BookPO getById25(BookPO bookPO) {
        return null;
    }

    @CacheEvict(namespaces = {"myCache1"}, cacheTypes = CacheType.MAP, key = "1", allowEmptyValue = true, needEvictBeforeProceed = true, needEvictAllInNamespaces = true)
    public BookPO getById26(BookPO bookPO) {
        return null;
    }

}
