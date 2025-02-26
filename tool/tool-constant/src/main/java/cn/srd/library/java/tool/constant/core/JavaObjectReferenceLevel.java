package cn.srd.library.java.tool.constant.core;

/**
 * Java 对象引用级别
 *
 * @author wjm
 * @since 2023-06-05 11:56:27
 */
public enum JavaObjectReferenceLevel {

    /**
     * 强引用（发生 GC 时，若对象没有被任何引用，则被回收）
     */
    STRONG,

    /**
     * 软引用（发生 GC 时，若内存不足，则被回收）
     */
    SOFT,

    /**
     * 弱引用（发生 GC 时，被回收）
     */
    WEAK,

    /**
     * 虚引用（随时被回收）
     */
    PHANTOM,

}
