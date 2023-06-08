package cn.srd.itcp.sugar.cache.all;

import cn.srd.itcp.sugar.cache.all.core.CacheConfig;
import cn.srd.itcp.sugar.cache.all.core.CacheRead;
import cn.srd.itcp.sugar.cache.all.support.CacheType;
import cn.srd.itcp.sugar.tool.core.RandomsUtil;
import org.springframework.stereotype.Component;

@CacheConfig(namespaces = {"myCache1", "myCache2"}, cacheTypes = {CacheType.MAP, CacheType.CAFFEINE, CacheType.REDIS}, enablePreventCachePenetrate = true)
@Component
public class BookService2 {

    @CacheRead(key = "#bookPO.name")
    public BookPO getByI2(BookPO bookPO) {
        return null;
    }

    public BookPO buildBook(Long id) {
        return BookPO.builder()
                .id(id)
                .name("bookName" + RandomsUtil.randomNumber())
                .build();
    }

}
