package cn.srd.library.java.oss.local.strategy;

import cn.srd.library.java.contract.component.oss.strategy.OssStorage;
import cn.srd.library.java.contract.model.throwable.UnsupportedException;

/**
 * @author wjm
 * @since 2024-07-17 16:29
 */
public class OssLocalStorage implements OssStorage {

    @Override
    public void registerFileStorageProperties(String bucketName) {
        throw new UnsupportedException();
    }

}