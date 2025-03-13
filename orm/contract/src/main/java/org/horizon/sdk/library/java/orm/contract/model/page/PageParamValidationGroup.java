package org.horizon.sdk.library.java.orm.contract.model.page;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.object.Nil;

import java.util.List;

/**
 * the page param validation group
 *
 * @author wjm
 * @since 2023-12-04 20:24
 */
public class PageParamValidationGroup implements DefaultGroupSequenceProvider<PageParam> {

    @Override
    public List<Class<?>> getValidationGroups(PageParam pageParam) {
        // the default validation group must be added
        List<Class<?>> defaultValidationGroups = Collections.ofArrayList(PageParam.class);
        // must ensure the instance being validated is not null
        if (Nil.isNotNull(pageParam) && pageParam.getPageIndex() > 1 && Nil.isNull(pageParam.getTotalNumber())) {
            defaultValidationGroups.add(TotalRecordNumberValidator.class);
        }
        return defaultValidationGroups;
    }

    public interface TotalRecordNumberValidator {

    }

}