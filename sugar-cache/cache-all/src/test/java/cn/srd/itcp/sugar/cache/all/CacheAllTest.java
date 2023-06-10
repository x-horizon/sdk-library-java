package cn.srd.itcp.sugar.cache.all;

import cn.srd.itcp.sugar.cache.all.core.EnableCache;
import lombok.SneakyThrows;
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

    @Autowired private BookService bookService;
    @Autowired private BookService2 bookService2;

    public void testCacheContextBuilding() {

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
