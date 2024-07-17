// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.java;

/**
 * java object reference level
 *
 * @author wjm
 * @since 2023-06-05 11:56
 */
public enum JavaObjectReferenceLevel {

    /**
     * the strong reference: the object will be recovered if it is not referenced in any way when GC.
     */
    STRONG,

    /**
     * the soft reference: the object will be recovered if it is out of memory when GC.
     */
    SOFT,

    /**
     * the weak reference: the object will be recovered when GC.
     */
    WEAK,

    /**
     * the phantom reference: the object will be recovered in any time.
     */
    PHANTOM,

    ;

}