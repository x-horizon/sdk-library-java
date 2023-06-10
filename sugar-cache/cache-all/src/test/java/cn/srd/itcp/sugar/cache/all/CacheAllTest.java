package cn.srd.itcp.sugar.cache.all;

import cn.srd.itcp.sugar.cache.all.core.EnableCache;
import cn.srd.itcp.sugar.cache.all.support.*;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@EnableCache
@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheAllTest {

    @Autowired private CacheContextWithoutCacheConfigBuildingService cacheContextWithoutCacheConfigBuildingService;
    @Autowired private CacheContextWithCacheConfigBuildingService cacheContextWithCacheConfigBuildingService;
    @Autowired private BookService bookService;
    @Autowired private BookService2 bookService2;

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
        cacheContextWithCacheConfigBuildingService.getById6(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById7(BOOK_PO1);
        // cache write
        cacheContextWithCacheConfigBuildingService.getById8(BOOK_PO1);
        Throwable result14 = Assert.assertThrows(RuntimeException.class, () -> cacheContextWithCacheConfigBuildingService.getById9(BOOK_PO1));
        cacheContextWithCacheConfigBuildingService.getById10(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById11(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById12(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById13(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById14(BOOK_PO1);
        // cache evict
        cacheContextWithCacheConfigBuildingService.getById15(BOOK_PO1);
        Throwable result15 = Assert.assertThrows(RuntimeException.class, () -> cacheContextWithCacheConfigBuildingService.getById16(BOOK_PO1));
        cacheContextWithCacheConfigBuildingService.getById17(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById18(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById19(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById20(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById21(BOOK_PO1);
        cacheContextWithCacheConfigBuildingService.getById22(BOOK_PO1);
    }

    @SneakyThrows
    @Test
    public void testCache() {
        // bookService.deleteAll();
        // BookPO bookPOAfterDelete11 = bookService.getById(1L);
        BookPO s1 = bookService2.getById1(bookService2.buildBook(3L));
        BookPO s2 = bookService2.getById1(bookService2.buildBook(3L));
        BookPO s3 = bookService2.getById2(bookService2.buildBook(3L));
        BookPO s4 = bookService2.getById2(bookService2.buildBook(3L));
        // BookPO s = bookService.getByI2(bookService.buildBook(3L));
        BookPO bookPOAfterDelete22 = bookService.getById(2L);
        BookPO bookPOAfterDelete33 = bookService.getById(3L);
        BookPO bookPOAfterDelete44 = bookService.getById(4L);
        BookPO bookPOAfterDelete55 = bookService.getById(5L);
        bookService.save(bookService.buildBook(3L));
        BookPO bookPO = bookService.getById(1L);

        bookService.save(bookService.buildBook(1L));
        bookService.save(bookService.buildBook(2L));
        bookService.save(bookService.buildBook(3L));
        bookService.save(bookService.buildBook(4L));
        bookService.save(bookService.buildBook(5L));

        BookPO bookPO1 = bookService.getById(1L);
        BookPO bookPO2 = bookService.getById(2L);
        BookPO bookPO3 = bookService.getById(3L);
        BookPO bookPO4 = bookService.getById(4L);
        BookPO bookPO5 = bookService.getById(5L);

        bookService.deleteById(1L);
        BookPO bookPOAfterDelete1 = bookService.getById(1L);
        bookService.deleteById(2L);
        BookPO bookPOAfterDelete2 = bookService.getById(2L);
        bookService.deleteById(3L);
        BookPO bookPOAfterDelete3 = bookService.getById(3L);
        bookService.deleteById(4L);
        BookPO bookPOAfterDelete4 = bookService.getById(4L);
        bookService.deleteById(5L);
        BookPO bookPOAfterDelete5 = bookService.getById(5L);

        bookService.save(bookService.buildBook(1L));
        bookService.save(bookService.buildBook(2L));
        bookService.save(bookService.buildBook(3L));
        bookService.save(bookService.buildBook(4L));
        bookService.save(bookService.buildBook(5L));

        bookService.deleteAll();
        // BookPO bookPOAfterDelete11 = bookService.getById(1L);
        // BookPO bookPOAfterDelete22 = bookService.getById(2L);
        // BookPO bookPOAfterDelete33 = bookService.getById(3L);
        // BookPO bookPOAfterDelete44 = bookService.getById(4L);
        // BookPO bookPOAfterDelete55 = bookService.getById(5L);

        TimeUnit.SECONDS.sleep(10L);
    }

}
