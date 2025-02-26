package org.horizon.library.java.tool.log.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;

/**
 * log color config
 *
 * @author wjm
 * @since 2023-03-17 09:28
 */
public class LogColorConfig extends ForegroundCompositeConverterBase<ILoggingEvent> {

    @Override
    protected String getForegroundColorCode(ILoggingEvent event) {
        return switch (event.getLevel().toInt()) {
            case Level.ERROR_INT -> ANSIConstants.RED_FG;
            case Level.WARN_INT -> ANSIConstants.MAGENTA_FG;
            case Level.INFO_INT -> ANSIConstants.WHITE_FG;
            case Level.DEBUG_INT -> ANSIConstants.DEFAULT_FG;
            default -> ANSIConstants.DEFAULT_FG;
        };
    }

}