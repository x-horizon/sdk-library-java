package cn.library.java.oss.minio.strategy;

import cn.library.java.contract.component.oss.model.enums.OssType;
import cn.library.java.contract.component.oss.strategy.OssStorage;
import cn.library.java.contract.constant.module.ModuleView;
import cn.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.library.java.oss.contract.Oss;
import cn.library.java.oss.contract.model.property.OssProperty;
import cn.library.java.tool.lang.compare.Comparators;
import cn.library.java.tool.lang.text.Strings;
import org.dromara.x.file.storage.core.FileStorageService;
import org.dromara.x.file.storage.core.FileStorageServiceBuilder;
import org.dromara.x.file.storage.spring.SpringFileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wjm
 * @since 2024-07-17 16:29
 */
public class OssMinioStorage implements OssStorage {

    @Autowired private OssProperty ossProperty;

    @Autowired private FileStorageService fileStorageService;

    @Override
    public void registerFileStorageProperties(String bucketName) {
        OssProperty.Config ossConfigProperty = ossProperty.getConfigs().stream()
                .filter(ossConfig -> Comparators.equals(OssType.MINIO, ossConfig.getType()))
                .findFirst()
                .orElseThrow(() -> new LibraryJavaInternalException(Strings.format("{}could not find the oss config, please provide the configuration for oss [{}].", ModuleView.OSS_SYSTEM, OssType.MINIO.getValue())));
        SpringFileStorageProperties.SpringMinioConfig fileStorageMinioConfig = new SpringFileStorageProperties.SpringMinioConfig();
        fileStorageMinioConfig.setEnableStorage(true)
                .setEndPoint(ossConfigProperty.getServerUrl())
                .setAccessKey(ossConfigProperty.getAccessKey())
                .setSecretKey(ossConfigProperty.getSecretKey())
                .setBucketName(bucketName)
                .setPlatform(Oss.getPlatform(OssType.MINIO, bucketName));
        fileStorageService.getFileStorageList().addAll(FileStorageServiceBuilder.buildMinioFileStorage(List.of(fileStorageMinioConfig), null));
    }

}