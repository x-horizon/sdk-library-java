package cn.srd.itcp.sugar.cache.all.test.support;

import cn.srd.itcp.sugar.tool.core.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.listener.MessageListener;

@Slf4j
@Getter
@RequiredArgsConstructor
public class CacheMessageListener implements MessageListener<CacheMessage> {

    private final RedisCaffeineCacheManager redisCaffeineCacheManager;

    @Override
    public void onMessage(CharSequence channel, CacheMessage cacheMessage) {
        if (!Objects.equals(cacheMessage.getServerId(), redisCaffeineCacheManager.getServerId())) {
            log.debug(
                    "receive a redis topic message, clear local cache, the cacheName is {}, operation is {}, the key is {}",
                    cacheMessage.getCacheName(), cacheMessage.getOperation(), cacheMessage.getKey()
            );
            redisCaffeineCacheManager.clearLocal(cacheMessage.getCacheName(), cacheMessage.getKey(), cacheMessage.getOperation());
        }
    }

}
