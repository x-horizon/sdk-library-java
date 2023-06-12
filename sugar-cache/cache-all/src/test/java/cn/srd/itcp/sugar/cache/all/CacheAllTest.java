package cn.srd.itcp.sugar.cache.all;

import cn.srd.itcp.sugar.cache.all.core.EnableCache;
import cn.srd.itcp.sugar.cache.all.support.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@EnableCache
@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheAllTest {

    @Autowired private CacheContextWithoutCacheConfigBuildingService cacheContextWithoutCacheConfigBuildingService;
    @Autowired private CacheContextWithCacheConfigBuildingService cacheContextWithCacheConfigBuildingService;
    @Autowired private CacheWithoutPreventCachePenetrateService cacheWithoutPreventCachePenetrateService;
    @Autowired private CacheWithPreventCachePenetrateService cacheWithPreventCachePenetrateService;

    private static final BookPO BOOK_PO1 = BookPO.build(1L);

    @Test
    public void testCacheContextBuilding() {
        // ======================== cache context without cache config building ========================
        // cache read
        Throwable result1 = Assert.assertThrows(RuntimeException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById1(BOOK_PO1));
        Throwable result2 = Assert.assertThrows(RuntimeException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById2(BOOK_PO1));
        Throwable result3 = Assert.assertThrows(RuntimeException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById3(BOOK_PO1));
        Throwable result4 = Assert.assertThrows(RuntimeException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById4(BOOK_PO1));
        cacheContextWithoutCacheConfigBuildingService.getById5(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById6(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById7(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById8(BOOK_PO1);
        // cache write
        Throwable result5 = Assert.assertThrows(RuntimeException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById9(BOOK_PO1));
        Throwable result6 = Assert.assertThrows(RuntimeException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById10(BOOK_PO1));
        Throwable result7 = Assert.assertThrows(RuntimeException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById11(BOOK_PO1));
        Throwable result8 = Assert.assertThrows(RuntimeException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById12(BOOK_PO1));
        cacheContextWithoutCacheConfigBuildingService.getById13(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById14(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById15(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById16(BOOK_PO1);
        // cache evict
        Throwable result9 = Assert.assertThrows(RuntimeException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById17(BOOK_PO1));
        Throwable result10 = Assert.assertThrows(RuntimeException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById18(BOOK_PO1));
        Throwable result11 = Assert.assertThrows(RuntimeException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById19(BOOK_PO1));
        Throwable result12 = Assert.assertThrows(RuntimeException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById20(BOOK_PO1));
        cacheContextWithoutCacheConfigBuildingService.getById21(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById22(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById23(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById24(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById25(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById26(BOOK_PO1);

        // ======================== cache context with cache config building ========================
        // cache read
        cacheContextWithCacheConfigBuildingService.getById1(BOOK_PO1);
        Throwable result13 = Assert.assertThrows(RuntimeException.class, () -> cacheContextWithCacheConfigBuildingService.getById2(BOOK_PO1));
        cacheContextWithCacheConfigBuildingService.getById3(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById4(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById5(BOOK_PO1);
        Throwable result14 = Assert.assertThrows(RuntimeException.class, () -> cacheContextWithCacheConfigBuildingService.getById6(BOOK_PO1));
        cacheContextWithCacheConfigBuildingService.getById7(BOOK_PO1);
        // cache write
        cacheContextWithCacheConfigBuildingService.getById8(BOOK_PO1);
        Throwable result15 = Assert.assertThrows(RuntimeException.class, () -> cacheContextWithCacheConfigBuildingService.getById9(BOOK_PO1));
        cacheContextWithCacheConfigBuildingService.getById10(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById11(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById12(BOOK_PO1);
        Throwable result16 = Assert.assertThrows(RuntimeException.class, () -> cacheContextWithCacheConfigBuildingService.getById13(BOOK_PO1));
        cacheContextWithCacheConfigBuildingService.getById14(BOOK_PO1);
        // cache evict
        cacheContextWithCacheConfigBuildingService.getById15(BOOK_PO1);
        Throwable result17 = Assert.assertThrows(RuntimeException.class, () -> cacheContextWithCacheConfigBuildingService.getById16(BOOK_PO1));
        cacheContextWithCacheConfigBuildingService.getById17(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById18(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById19(BOOK_PO1);
        Throwable result18 = Assert.assertThrows(RuntimeException.class, () -> cacheContextWithCacheConfigBuildingService.getById20(BOOK_PO1));
        cacheContextWithCacheConfigBuildingService.getById21(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById22(BOOK_PO1);
    }

    @Test
    public void testCache() {
        BookPO result1 = cacheWithoutPreventCachePenetrateService.getById(1L);
        BookPO result2 = cacheWithoutPreventCachePenetrateService.getById(1L);
        BookPO result3 = cacheWithoutPreventCachePenetrateService.getNull(8L);
        BookPO result4 = cacheWithoutPreventCachePenetrateService.getNull(8L);
        BookPO result5 = cacheWithoutPreventCachePenetrateService.save(BookPO.build(102L));
        BookPO result6 = cacheWithoutPreventCachePenetrateService.save(BookPO.build(103L));
        BookPO result7 = cacheWithoutPreventCachePenetrateService.saveNull(BookPO.build(103L));
        BookPO result8 = cacheWithoutPreventCachePenetrateService.saveNull(BookPO.build(103L));
        BookPO result9 = cacheWithoutPreventCachePenetrateService.deleteById(1L);
        BookPO result10 = cacheWithoutPreventCachePenetrateService.deleteById(1L);
        BookPO result11 = cacheWithoutPreventCachePenetrateService.deleteAll1(1L);
        BookPO result12 = cacheWithoutPreventCachePenetrateService.deleteAll1(1L);
        BookPO result13 = cacheWithoutPreventCachePenetrateService.deleteAll2(1L);
        BookPO result14 = cacheWithoutPreventCachePenetrateService.deleteAll2(1L);
        BookPO result15 = cacheWithoutPreventCachePenetrateService.deleteBeforeProceedById(1L);
        BookPO result16 = cacheWithoutPreventCachePenetrateService.deleteBeforeProceedById(1L);
        BookPO result17 = cacheWithoutPreventCachePenetrateService.deleteBeforeProceedAll(1L);
        BookPO result18 = cacheWithoutPreventCachePenetrateService.deleteBeforeProceedAll(1L);
        BookPO result19 = cacheWithoutPreventCachePenetrateService.multi(BookPO.build(200L));
        BookPO result20 = cacheWithoutPreventCachePenetrateService.multi(BookPO.build(200L));

        BookPO result22 = cacheWithPreventCachePenetrateService.getById(1L);
        BookPO result23 = cacheWithPreventCachePenetrateService.getById(1L);
        BookPO result24 = cacheWithPreventCachePenetrateService.saveNull(BookPO.build(103L));
        BookPO result25 = cacheWithPreventCachePenetrateService.saveNull(BookPO.build(103L));
        BookPO result26 = cacheWithPreventCachePenetrateService.getById(103L);
        BookPO result27 = cacheWithPreventCachePenetrateService.getById(103L);
        BookPO result28 = cacheWithPreventCachePenetrateService.getNull(8L);
        BookPO result29 = cacheWithPreventCachePenetrateService.getNull(8L);
        BookPO result30 = cacheWithPreventCachePenetrateService.save(BookPO.build(102L));
        BookPO result31 = cacheWithPreventCachePenetrateService.save(BookPO.build(103L));
        BookPO result32 = cacheWithPreventCachePenetrateService.deleteById(1L);
        BookPO result33 = cacheWithPreventCachePenetrateService.deleteById(1L);
        BookPO result34 = cacheWithPreventCachePenetrateService.deleteAll1(1L);
        BookPO result35 = cacheWithPreventCachePenetrateService.deleteAll1(1L);
        BookPO result36 = cacheWithPreventCachePenetrateService.deleteAll2(1L);
        BookPO result37 = cacheWithPreventCachePenetrateService.deleteAll2(1L);
        BookPO result38 = cacheWithPreventCachePenetrateService.deleteBeforeProceedById(1L);
        BookPO result39 = cacheWithPreventCachePenetrateService.deleteBeforeProceedById(1L);
        BookPO result40 = cacheWithPreventCachePenetrateService.deleteBeforeProceedAll(1L);
        BookPO result41 = cacheWithPreventCachePenetrateService.deleteBeforeProceedAll(1L);
        BookPO result42 = cacheWithPreventCachePenetrateService.multi(BookPO.build(200L));
        BookPO result43 = cacheWithPreventCachePenetrateService.multi(BookPO.build(200L));
    }

}
