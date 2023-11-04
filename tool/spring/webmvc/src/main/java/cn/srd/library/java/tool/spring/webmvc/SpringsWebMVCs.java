// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.webmvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * toolkit for spring web mvc
 *
 * @author wjm
 * @since 2022-08-05 22:53
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringsWebMVCs {

    /**
     * get {@link ServletRequestAttributes}
     *
     * @return {@link ServletRequestAttributes}
     */
    @NonNull
    public static ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    }

    /**
     * get {@link HttpServletRequest}
     *
     * @return {@link HttpServletRequest}
     */
    public static HttpServletRequest getHttpServletRequest() {
        return getServletRequestAttributes().getRequest();
    }

    /**
     * get {@link HttpServletResponse}
     *
     * @return {@link HttpServletResponse}
     */
    public static HttpServletResponse getHttpServletResponse() {
        return getServletRequestAttributes().getResponse();
    }

}
