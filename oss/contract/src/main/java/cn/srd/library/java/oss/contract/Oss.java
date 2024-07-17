// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.contract;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.suppress.SuppressWarningConstant;
import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.contract.constant.web.ProtocolConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.oss.contract.constant.OssConstant;
import cn.srd.library.java.oss.contract.model.enums.OssType;
import cn.srd.library.java.oss.contract.model.property.OssProperty;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.convert.Converts;
import cn.srd.library.java.tool.lang.net.Urls;
import cn.srd.library.java.tool.lang.text.Strings;
import lombok.SneakyThrows;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

/**
 * @author wjm
 * @since 2024-07-17 19:12
 */
@SuppressWarnings(SuppressWarningConstant.PREVIEW)
public class Oss {

    private static final Map<String, Boolean> alreadyRegisterPlatforms = Collections.newConcurrentHashMap();

    @SneakyThrows
    public static void upload(File file, String url) {
        String protocol = Urls.getProtocol(url);
        OssType ossType = Optional.ofNullable(Converts.toEnumByValue(protocol, OssType.class))
                .orElseThrow(() -> new LibraryJavaInternalException(Strings.format("{}unsupported oss type [{}], current supported oss types are {}, please check!", ModuleView.OSS_SYSTEM, protocol, Arrays.stream(OssType.values()).map(OssType::getDescription).toList())));
        CharSequence bucketName = Optional.ofNullable(Urls.getQueryParam(url, OssConstant.BUCKET_NAME))
                .orElseThrow(() -> new LibraryJavaInternalException(Strings.format("{}could not parse bucket name from url [{}], example url like: [minio://my.minio.com?{}=myBucketName], please check!", ModuleView.OSS_SYSTEM, url, OssConstant.BUCKET_NAME)));
        OssProperty.Config ossConfigProperty = ossType.getStorage().getOssConfigProperty();
        String actualUrl = Strings.insertFirst(url, ProtocolConstant.SEPARATOR, Urls.getAuthority(ossConfigProperty.getServerUrl()) + SymbolConstant.SLASH);
        String path = Urls.getUri(actualUrl);
        upload(ossType, bucketName.toString(), file, file.getName(), path);
    }

    public static void upload(OssType ossType, String bucketName, File file, String path) {
        upload(ossType, bucketName, file, file.getName(), path);
    }

    public static void upload(OssType ossType, String bucketName, Object file, String filename, String path) {
        if (needToRegisterPlatform(ossType, bucketName)) {
            ossType.getStorage().registerFileStorageProperties(bucketName);
            alreadyRegisterPlatforms.put(getPlatform(ossType, bucketName), Boolean.TRUE);
        }
        ossType.getStorage().upload(file, path, filename);
    }

    public static String getPlatform(OssType ossType, String bucketName) {
        return STR."\{ossType.getDescription()}-\{bucketName}";
    }

    private static boolean needToRegisterPlatform(OssType ossType, String bucketName) {
        return Collections.notContainsKey(alreadyRegisterPlatforms, getPlatform(ossType, bucketName));
    }

}