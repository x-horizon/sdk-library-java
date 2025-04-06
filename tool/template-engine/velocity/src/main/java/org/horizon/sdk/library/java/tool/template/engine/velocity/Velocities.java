package org.horizon.sdk.library.java.tool.template.engine.velocity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;

/**
 * toolkit for velocity
 *
 * @author wjm
 * @since 2025-04-06 17:05
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Velocities {

    private static final String DEFAULT_VELOCITY_LOG_NAME = "defaultVelocityLog";

    static {
        Velocity.init();
    }

    public static String evaluate(VelocityContext velocityContext, String template) {
        return evaluate(velocityContext, DEFAULT_VELOCITY_LOG_NAME, template);
    }

    public static String evaluate(VelocityContext velocityContext, String logName, String template) {
        StringWriter writer = new StringWriter();
        Velocity.evaluate(velocityContext, writer, logName, template);
        return writer.toString();
    }

}