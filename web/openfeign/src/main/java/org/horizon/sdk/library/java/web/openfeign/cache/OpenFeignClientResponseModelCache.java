package org.horizon.sdk.library.java.web.openfeign.cache;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.contract.model.protocol.TransportModel;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;

import java.util.HashSet;
import java.util.Set;

/**
 * response models cache
 *
 * @author wjm
 * @since 2023-03-04 16:48
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
public class OpenFeignClientResponseModelCache {

    /**
     * the response models to resolve
     */
    private static final Set<Class<? extends TransportModel>> RESOLVED_RESPONSE_MODELS = new HashSet<>();

    /**
     * get the response models
     *
     * @return the response models
     */
    public static Set<Class<? extends TransportModel>> get() {
        return RESOLVED_RESPONSE_MODELS;
    }

    /**
     * set the response models
     *
     * @param resolvedModels the response models to be resolved
     */
    public static void set(Set<Class> resolvedModels) {
        Collections.add(RESOLVED_RESPONSE_MODELS, resolvedModels);
    }

}