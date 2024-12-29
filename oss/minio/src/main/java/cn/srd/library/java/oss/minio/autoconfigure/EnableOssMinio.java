package cn.srd.library.java.oss.minio.autoconfigure;

import org.dromara.x.file.storage.spring.FileStorageAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * provide an annotation to enable oss minio system.
 *
 * @author wjm
 * @since 2024-07-16 17:00
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({OssMinioRegistrar.class, FileStorageAutoConfiguration.class})
public @interface EnableOssMinio {

}