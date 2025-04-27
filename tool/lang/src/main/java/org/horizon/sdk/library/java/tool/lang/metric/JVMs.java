package org.horizon.sdk.library.java.tool.lang.metric;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wjm
 * @since 2025-04-27 17:35
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JVMs {

    private static final RuntimeMXBean RUNTIME_MX_BEAN = ManagementFactory.getRuntimeMXBean();

    private static final MemoryMXBean MEMORY_MX_BEAN = ManagementFactory.getMemoryMXBean();

    private static final double INIT_HEAP_MEMORY_MB = MEMORY_MX_BEAN.getHeapMemoryUsage().getInit() / 1024.0 / 1024.0;

    private static final double MAX_HEAP_MEMORY_MB = MEMORY_MX_BEAN.getHeapMemoryUsage().getMax() / 1024.0 / 1024.0;

    private static final String GARBAGE_COLLECTOR_NAME = ManagementFactory.getGarbageCollectorMXBeans().stream().map(GarbageCollectorMXBean::getName).collect(Collectors.joining(SymbolConstant.COMMA_CHINESE));

    public static List<String> getInputArguments() {
        return RUNTIME_MX_BEAN.getInputArguments();
    }

    public static String getJavaInfo() {
        return Strings.format("{}:{}:{}", RUNTIME_MX_BEAN.getVmVendor(), RUNTIME_MX_BEAN.getVmName(), RUNTIME_MX_BEAN.getVmVersion());
    }

    public static double getInitHeapMemoryMB() {
        return INIT_HEAP_MEMORY_MB;
    }

    public static double getMaxHeapMemoryMB() {
        return MAX_HEAP_MEMORY_MB;
    }

    public static String getGarbageCollectorName() {
        return GARBAGE_COLLECTOR_NAME;
    }

}