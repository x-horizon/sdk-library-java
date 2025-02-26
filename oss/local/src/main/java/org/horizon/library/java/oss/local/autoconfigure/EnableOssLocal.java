package org.horizon.library.java.oss.local.autoconfigure;

import org.dromara.x.file.storage.spring.FileStorageAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * provide an annotation to enable oss local system.
 *
 * @author wjm
 * @since 2024-07-17 16:35
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({OssLocalRegistrar.class, FileStorageAutoConfiguration.class})
public @interface EnableOssLocal {

}