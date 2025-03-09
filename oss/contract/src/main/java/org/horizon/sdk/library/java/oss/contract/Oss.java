package org.horizon.sdk.library.java.oss.contract;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.dromara.x.file.storage.core.Downloader;
import org.dromara.x.file.storage.core.FileStorageService;
import org.dromara.x.file.storage.core.ProgressListener;
import org.dromara.x.file.storage.core.upload.UploadPretreatment;
import org.horizon.sdk.library.java.contract.component.oss.model.enums.OssType;
import org.horizon.sdk.library.java.contract.constant.module.ModuleView;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.contract.model.throwable.LibraryJavaInternalException;
import org.horizon.sdk.library.java.oss.contract.constant.OssConstant;
import org.horizon.sdk.library.java.oss.contract.model.domain.OssFileDO;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;
import org.horizon.sdk.library.java.tool.lang.net.Urls;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.text.Strings;
import org.horizon.sdk.library.java.tool.spring.contract.support.Springs;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author wjm
 * @since 2024-07-17 19:12
 */
public class Oss {

    private static final Map<String, Boolean> ALREADY_REGISTER_PLATFORM_CACHE = Collections.newConcurrentHashMap();

    private static final int DEFAULT_SCALE_SIZE = 1000;

    private static final int DEFAULT_THUMBNAIL_SIZE = 200;

    public static UploadChainer openUpload() {
        return new UploadChainer();
    }

    public static Downloader download(String url, OssFileDO ossFileDO) {
        registerFileStoragePropertiesIfNeed(url);
        return Springs.getBean(FileStorageService.class).download(ossFileDO.toFileInfo());
    }

    public static Downloader downloadThumbnail(String url, OssFileDO ossFileDO) {
        registerFileStoragePropertiesIfNeed(url);
        return Springs.getBean(FileStorageService.class).downloadTh(ossFileDO.toFileInfo());
    }

    public static boolean delete(String url, OssFileDO ossFileDO) {
        registerFileStoragePropertiesIfNeed(url);
        return Springs.getBean(FileStorageService.class).delete(ossFileDO.toFileInfo());
    }

    public static boolean exist(String url, OssFileDO ossFileDO) {
        registerFileStoragePropertiesIfNeed(url);
        return Springs.getBean(FileStorageService.class).exists(ossFileDO.toFileInfo());
    }

    public static boolean notExist(String url, OssFileDO ossFileDO) {
        return !exist(url, ossFileDO);
    }

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    public static String getPlatform(OssType ossType, String bucketName) {
        return STR."\{ossType.getValue()}-\{bucketName}";
    }

    private static OssType parseOssType(String url) {
        String protocol = Urls.getProtocol(url);
        return Optional.ofNullable(Converts.toEnumByValue(protocol, OssType.class))
                .orElseThrow(() -> new LibraryJavaInternalException(Strings.format("{}unsupported oss type [{}], current supported oss types are {}, please check!", ModuleView.OSS_SYSTEM, protocol, Arrays.stream(OssType.values()).map(OssType::getValue).toList())));
    }

    private static String parseBucketName(String url) {
        return Optional.ofNullable(Urls.getQueryParam(url, OssConstant.BUCKET_NAME))
                .orElseThrow(() -> new LibraryJavaInternalException(Strings.format("{}could not parse bucket name from url [{}], example url like: [minio:///foo/test?{}=myBucketName], please check!", ModuleView.OSS_SYSTEM, url, OssConstant.BUCKET_NAME)))
                .toString();
    }

    private static String parsePath(String url) {
        return Urls.getUri(url);
    }

    private static void registerFileStoragePropertiesIfNeed(String url) {
        registerFileStoragePropertiesIfNeed(parseOssType(url), parseBucketName(url));
    }

    private static void registerFileStoragePropertiesIfNeed(OssType ossType, String bucketName) {
        ALREADY_REGISTER_PLATFORM_CACHE.computeIfAbsent(getPlatform(ossType, bucketName), ignore -> {
            ossType.getStorage().registerFileStorageProperties(bucketName);
            return Boolean.TRUE;
        });
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UploadChainer {

        private Consumer<Thumbnails.Builder<? extends InputStream>> scale;

        private Consumer<Thumbnails.Builder<? extends InputStream>> thumbnail;

        private ProgressListener progressListener;

        public UploadChainer scale() {
            this.scale = image -> image.size(DEFAULT_SCALE_SIZE, DEFAULT_SCALE_SIZE);
            return this;
        }

        public UploadChainer scale(Consumer<Thumbnails.Builder<? extends InputStream>> scale) {
            this.scale = scale;
            return this;
        }

        public UploadChainer thumbnail() {
            this.thumbnail = image -> image.size(DEFAULT_THUMBNAIL_SIZE, DEFAULT_THUMBNAIL_SIZE);
            return this;
        }

        public UploadChainer thumbnail(Consumer<Thumbnails.Builder<? extends InputStream>> thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public UploadChainer progressListener(Consumer<Thumbnails.Builder<? extends InputStream>> thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public UploadChainer progressListener(BiConsumer<Long, Long> progressListener) {
            return progressListener(new ProgressListener() {
                @Override
                public void start() {
                }

                @Override
                public void progress(long alreadyUploadSize, Long totalSize) {
                    progressListener.accept(alreadyUploadSize, totalSize);
                }

                @Override
                public void finish() {
                }
            });
        }

        public UploadChainer progressListener(ProgressListener progressListener) {
            this.progressListener = progressListener;
            return this;
        }

        public OssFileDO upload(Object file, String url) {
            return upload(file, null, url);
        }

        public OssFileDO upload(Object file, String filename, String url) {
            return upload(parseOssType(url), parseBucketName(url), file, filename, parsePath(url));
        }

        public OssFileDO upload(OssType ossType, String bucketName, File file, String path) {
            return upload(ossType, bucketName, file, file.getName(), path);
        }

        public OssFileDO upload(OssType ossType, String bucketName, Object file, String path) {
            return upload(ossType, bucketName, file, null, path);
        }

        public OssFileDO upload(OssType ossType, String bucketName, Object file, String filename, String path) {
            registerFileStoragePropertiesIfNeed(ossType, bucketName);
            UploadPretreatment uploadPretreatment = Springs.getBean(FileStorageService.class)
                    .of(file)
                    .setPlatform(getPlatform(ossType, bucketName))
                    .setPath(Nil.isBlank(path) ? SymbolConstant.EMPTY : Strings.removeHeadTailSlash(path) + SymbolConstant.SLASH)
                    .setSaveFilename(filename)
                    .setProgressListener(this.progressListener);
            if (Nil.isNotNull(this.scale)) {
                uploadPretreatment.image(this.scale);
            }
            if (Nil.isNotNull(this.thumbnail)) {
                uploadPretreatment.thumbnail(this.thumbnail);
            }
            return OssFileDO.from(uploadPretreatment.upload());
        }

    }

}