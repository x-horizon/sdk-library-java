package org.horizon.sdk.library.java.tool.spring.contract.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.horizon.sdk.library.java.tool.lang.metric.JVMs;
import org.horizon.sdk.library.java.tool.lang.text.Strings;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

/**
 * @author wjm
 * @since 2025-04-27 21:00
 */
@Slf4j
public class JvmPrinter {

    @EventListener(classes = ApplicationReadyEvent.class)
    public void print() {
        log.info("""
                        {}
                        java info        : {}
                        init heap memory : {} MB
                        max  heap memory : {} MB
                        garbage collector: {}
                        """,
                Strings.format("\ninput argument   : {}", Strings.join(JVMs.getInputArguments(), "\ninput argument   : ")),
                JVMs.getJavaInfo(),
                JVMs.getInitHeapMemoryMB(),
                JVMs.getMaxHeapMemoryMB(),
                JVMs.getGarbageCollectorName()
        );
    }

}