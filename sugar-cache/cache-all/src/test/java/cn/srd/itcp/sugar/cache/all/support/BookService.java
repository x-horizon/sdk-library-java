package cn.srd.itcp.sugar.cache.all.support;

import cn.srd.itcp.sugar.tool.core.RandomsUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@CacheConfig(cacheNames = {"myCache1", "myCache2"})
@Component
public class BookService {

    @Cacheable(key = "#id")
    public BookPO getById(Long id) {
        return null;
    }

    @CachePut(key = "#bookPO.id")
    public BookPO save(BookPO bookPO) {
        return bookPO;
    }

    @CacheEvict(key = "#id")
    public void deleteById(Long id) {
        System.out.println();
    }

    @CacheEvict(allEntries = true)
    public void deleteAll() {
    }

    public BookPO buildBook(Long id) {
        return BookPO.builder()
                .id(id)
                .name("bookName" + RandomsUtil.randomNumber())
                .build();
    }

}
