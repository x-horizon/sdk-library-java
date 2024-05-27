package cn.srd.library.java.cache.all.test;

import cn.srd.library.java.cache.all.autoconfigue.EnableCache;
import cn.srd.library.java.cache.all.model.BookPO;
import cn.srd.library.java.cache.all.service.CacheAllowEmptyValueService;
import cn.srd.library.java.cache.all.service.CacheContextWithCacheConfigBuildingService;
import cn.srd.library.java.cache.all.service.CacheContextWithoutCacheConfigBuildingService;
import cn.srd.library.java.cache.all.service.CacheNotAllowEmptyValueService;
import cn.srd.library.java.concurrent.redis.EnableRedisLock;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@EnableCache
@EnableRedisLock
@ExtendWith(SpringExtension.class)
@SpringBootTest
class CacheAllTest {

    @Autowired private CacheContextWithoutCacheConfigBuildingService cacheContextWithoutCacheConfigBuildingService;

    @Autowired private CacheContextWithCacheConfigBuildingService cacheContextWithCacheConfigBuildingService;

    @Autowired private CacheNotAllowEmptyValueService cacheNotAllowEmptyValueService;

    @Autowired private CacheAllowEmptyValueService cacheAllowEmptyValueService;

    private static final BookPO BOOK_PO1 = BookPO.build(1L);

    @Test
    void testCacheContextBuilding() {
        // ======================== cache context without cache config building ========================
        // cache read
        Throwable result1 = Assertions.assertThrowsExactly(LibraryJavaInternalException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById1(BOOK_PO1));
        Throwable result2 = Assertions.assertThrowsExactly(LibraryJavaInternalException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById2(BOOK_PO1));
        Throwable result3 = Assertions.assertThrowsExactly(LibraryJavaInternalException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById3(BOOK_PO1));
        Throwable result4 = Assertions.assertThrowsExactly(LibraryJavaInternalException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById4(BOOK_PO1));
        cacheContextWithoutCacheConfigBuildingService.getById5(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById6(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById7(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById8(BOOK_PO1);
        // cache write
        Throwable result5 = Assertions.assertThrowsExactly(LibraryJavaInternalException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById9(BOOK_PO1));
        Throwable result6 = Assertions.assertThrowsExactly(LibraryJavaInternalException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById10(BOOK_PO1));
        cacheContextWithoutCacheConfigBuildingService.getById12(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById13(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById14(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById15(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById16(BOOK_PO1);
        // cache evict
        Throwable result9 = Assertions.assertThrowsExactly(LibraryJavaInternalException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById17(BOOK_PO1));
        Throwable result10 = Assertions.assertThrowsExactly(LibraryJavaInternalException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById18(BOOK_PO1));
        Throwable result11 = Assertions.assertThrowsExactly(LibraryJavaInternalException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById19(BOOK_PO1));
        Throwable result12 = Assertions.assertThrowsExactly(LibraryJavaInternalException.class, () -> cacheContextWithoutCacheConfigBuildingService.getById20(BOOK_PO1));
        cacheContextWithoutCacheConfigBuildingService.getById21(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById22(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById23(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById24(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById25(BOOK_PO1);
        cacheContextWithoutCacheConfigBuildingService.getById26(BOOK_PO1);

        // ======================== cache context with cache config building ========================
        // cache read
        cacheContextWithCacheConfigBuildingService.getById1(BOOK_PO1);
        Throwable result13 = Assertions.assertThrowsExactly(LibraryJavaInternalException.class, () -> cacheContextWithCacheConfigBuildingService.getById2(BOOK_PO1));
        cacheContextWithCacheConfigBuildingService.getById3(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById4(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById5(BOOK_PO1);
        Throwable result14 = Assertions.assertThrowsExactly(LibraryJavaInternalException.class, () -> cacheContextWithCacheConfigBuildingService.getById6(BOOK_PO1));
        cacheContextWithCacheConfigBuildingService.getById7(BOOK_PO1);
        // cache write
        cacheContextWithCacheConfigBuildingService.getById8(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById9(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById10(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById11(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById12(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById14(BOOK_PO1);
        // cache evict
        cacheContextWithCacheConfigBuildingService.getById15(BOOK_PO1);
        Throwable result17 = Assertions.assertThrowsExactly(LibraryJavaInternalException.class, () -> cacheContextWithCacheConfigBuildingService.getById16(BOOK_PO1));
        cacheContextWithCacheConfigBuildingService.getById17(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById18(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById19(BOOK_PO1);
        Throwable result18 = Assertions.assertThrowsExactly(LibraryJavaInternalException.class, () -> cacheContextWithCacheConfigBuildingService.getById20(BOOK_PO1));
        cacheContextWithCacheConfigBuildingService.getById21(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById22(BOOK_PO1);
    }

    @Test
    void testCache() {
        cacheAllowEmptyValueService.saveBatch(List.of(BookPO.builder().id(200L).build(), BookPO.builder().id(201L).build()));

        List<BookPO> result109 = cacheAllowEmptyValueService.getAll2();
        List<BookPO> result121 = cacheAllowEmptyValueService.getAll2();
        List<BookPO> result122 = cacheAllowEmptyValueService.getAll2();

        List<BookPO> result131 = cacheNotAllowEmptyValueService.getAll2();
        List<BookPO> result132 = cacheNotAllowEmptyValueService.getAll2();
        List<BookPO> result133 = cacheNotAllowEmptyValueService.getAll2();

        BookPO result1 = cacheNotAllowEmptyValueService.getById(1L);
        BookPO result2 = cacheNotAllowEmptyValueService.getById(1L);
        BookPO result3 = cacheNotAllowEmptyValueService.getNull(8L);
        BookPO result4 = cacheNotAllowEmptyValueService.getNull(8L);
        BookPO result5 = cacheNotAllowEmptyValueService.save(BookPO.build(1L));
        BookPO result6 = cacheNotAllowEmptyValueService.getById(1L);
        BookPO result7 = cacheNotAllowEmptyValueService.save(BookPO.build(102L));
        BookPO result8 = cacheNotAllowEmptyValueService.save(BookPO.build(103L));
        BookPO result9 = cacheNotAllowEmptyValueService.saveNull(BookPO.build(103L));
        BookPO result10 = cacheNotAllowEmptyValueService.saveNull(BookPO.build(103L));
        BookPO result11 = cacheNotAllowEmptyValueService.getById(102L);
        BookPO result12 = cacheNotAllowEmptyValueService.getById(103L);
        BookPO result111 = cacheNotAllowEmptyValueService.getById(105L);
        BookPO result112 = cacheNotAllowEmptyValueService.getById(106L);
        cacheNotAllowEmptyValueService.deleteByIds(List.of(105L, 106L));
        cacheNotAllowEmptyValueService.deleteById(1L);
        cacheNotAllowEmptyValueService.deleteById(1L);
        cacheNotAllowEmptyValueService.deleteAll1(1L);
        cacheNotAllowEmptyValueService.deleteAll1(1L);
        cacheNotAllowEmptyValueService.deleteAll2(1L);
        cacheNotAllowEmptyValueService.deleteAll2(1L);
        cacheNotAllowEmptyValueService.deleteBeforeProceedById(1L);
        cacheNotAllowEmptyValueService.deleteBeforeProceedById(1L);
        cacheNotAllowEmptyValueService.deleteBeforeProceedAll(1L);
        cacheNotAllowEmptyValueService.deleteBeforeProceedAll(1L);
        BookPO result23 = cacheNotAllowEmptyValueService.multi(BookPO.build(200L));
        BookPO result24 = cacheNotAllowEmptyValueService.multi(BookPO.build(200L));
        List<BookPO> result101 = cacheNotAllowEmptyValueService.getAll();

        BookPO result25 = cacheAllowEmptyValueService.getById(1L);
        BookPO result103 = cacheAllowEmptyValueService.getById2(1L);
        BookPO result104 = cacheAllowEmptyValueService.getById2(1L);
        BookPO result26 = cacheAllowEmptyValueService.getById(1L);
        BookPO result27 = cacheAllowEmptyValueService.saveNull(BookPO.build(103L));
        BookPO result28 = cacheAllowEmptyValueService.saveNull(BookPO.build(103L));
        BookPO result29 = cacheAllowEmptyValueService.getById(103L);
        BookPO result30 = cacheAllowEmptyValueService.getById(103L);
        BookPO result98 = cacheAllowEmptyValueService.getById(105L);
        BookPO result99 = cacheAllowEmptyValueService.getById(106L);
        BookPO result31 = cacheAllowEmptyValueService.getNull(8L);
        BookPO result32 = cacheAllowEmptyValueService.getNull(8L);
        BookPO result119 = cacheAllowEmptyValueService.save2(BookPO.build(1L).setAuthor("author"));
        BookPO result33 = cacheAllowEmptyValueService.save(BookPO.build(102L));
        BookPO result34 = cacheAllowEmptyValueService.save(BookPO.build(103L));
        cacheAllowEmptyValueService.deleteByIds(List.of(105L, 106L));
        cacheAllowEmptyValueService.deleteById(1L);
        cacheAllowEmptyValueService.deleteById(1L);
        cacheAllowEmptyValueService.deleteAll1(1L);
        cacheAllowEmptyValueService.deleteAll1(1L);
        cacheAllowEmptyValueService.deleteAll2(1L);
        cacheAllowEmptyValueService.deleteAll2(1L);
        cacheAllowEmptyValueService.deleteBeforeProceedById(1L);
        cacheAllowEmptyValueService.deleteBeforeProceedById(1L);
        cacheAllowEmptyValueService.deleteBeforeProceedAll(1L);
        cacheAllowEmptyValueService.deleteBeforeProceedAll(1L);
        BookPO result45 = cacheAllowEmptyValueService.multi(BookPO.build(200L));
        BookPO result46 = cacheAllowEmptyValueService.multi(BookPO.build(200L));
        List<BookPO> result102 = cacheAllowEmptyValueService.getAll();
    }

}