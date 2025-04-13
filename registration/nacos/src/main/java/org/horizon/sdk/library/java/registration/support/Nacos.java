package org.horizon.sdk.library.java.registration.support;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.horizon.sdk.library.java.contract.constant.module.ModuleView;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.contract.model.throwable.DataNotFoundException;
import org.horizon.sdk.library.java.tool.convert.api.Converts;
import org.horizon.sdk.library.java.tool.spring.contract.support.Springs;

import java.util.Optional;

/**
 * @author wjm
 * @since 2025-04-13 18:16
 */
@SuppressWarnings(SuppressWarningConstant.PREVIEW)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Nacos {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Discovery {

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Config {

        private static final long DEFAULT_GET_CONFIG_TIME_OUT_MILLISECOND = 3000L;

        public static String get(String dataId) {
            return get(dataId, getDefaultGroup(), DEFAULT_GET_CONFIG_TIME_OUT_MILLISECOND);
        }

        public static String get(String dataId, String group) {
            return get(dataId, group, DEFAULT_GET_CONFIG_TIME_OUT_MILLISECOND);
        }

        @SneakyThrows
        public static String get(String dataId, String group, long timeoutMillisecond) {
            return getManager().getConfigService().getConfig(dataId, group, timeoutMillisecond);
        }

        public static String get(String dataId, Listener listener) {
            return get(dataId, getDefaultGroup(), listener);
        }

        public static String get(String dataId, String group, Listener listener) {
            return get(dataId, group, DEFAULT_GET_CONFIG_TIME_OUT_MILLISECOND, listener);
        }

        @SneakyThrows
        public static String get(String dataId, String group, long timeoutMillisecond, Listener listener) {
            return getManager().getConfigService().getConfigAndSignListener(dataId, group, timeoutMillisecond, listener);
        }

        public static boolean publish(String dataId, String content) {
            return publish(dataId, getDefaultGroup(), content);
        }

        public static boolean publish(String dataId, String group, String content) {
            return publish(dataId, group, getDefaultFileExtensionType(), content);
        }

        public static boolean publish(String dataId, ConfigType configType, String content) {
            return publish(dataId, getDefaultGroup(), configType, content);
        }

        @SneakyThrows
        public static boolean publish(String dataId, String group, ConfigType configType, String content) {
            return getManager().getConfigService().publishConfig(dataId, group, content, configType.getType());
        }

        private static String getDefaultGroup() {
            return getManager().getNacosConfigProperties().getGroup();
        }

        private static ConfigType getDefaultFileExtensionType() {
            return getFileExtensionType(getManager().getNacosConfigProperties().getFileExtension());
        }

        private static ConfigType getFileExtensionType(String fileExtensionType) {
            return Optional.ofNullable(Converts.toEnumByValue(fileExtensionType, ConfigType.class))
                    .orElseThrow(() -> new DataNotFoundException(STR."\{ModuleView.REGISTRATION_NACOS_SYSTEM}invalid file extension type: \{fileExtensionType}"));
        }

        private static NacosConfigManager getManager() {
            return NacosConfigManagerHolder.INSTANCE;
        }

        private static class NacosConfigManagerHolder {

            private static final NacosConfigManager INSTANCE = Springs.getBean(NacosConfigManager.class);

        }

    }

}