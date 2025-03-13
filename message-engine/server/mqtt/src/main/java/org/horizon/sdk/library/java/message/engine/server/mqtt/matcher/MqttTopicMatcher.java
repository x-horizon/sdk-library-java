package org.horizon.sdk.library.java.message.engine.server.mqtt.matcher;

import lombok.Getter;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.tool.lang.functional.Assert;
import org.horizon.sdk.library.java.tool.lang.object.Nil;

import java.util.regex.Pattern;

/**
 * @author wjm
 * @since 2025-01-05 20:37
 */
@SuppressWarnings(SuppressWarningConstant.PREVIEW)
public class MqttTopicMatcher {

    @Getter private final String topic;

    private final Pattern topicRegex;

    public MqttTopicMatcher(String topic) {
        Assert.of("topic cannot be blank, please check!").throwsIfBlank(topic);
        this.topic = topic;
        this.topicRegex = Pattern.compile(STR."\{topic.replace("+", "[^/]+").replace("#", ".+")}$");
    }

    public boolean match(String topic) {
        return this.topicRegex.matcher(topic).matches();
    }

    @Override
    public boolean equals(Object comparedObject) {
        if (this == comparedObject) {
            return true;
        }
        if (Nil.isNull(comparedObject) || getClass() != comparedObject.getClass()) {
            return false;
        }
        return this.topic.equals(((MqttTopicMatcher) comparedObject).getTopic());
    }

    @Override
    public int hashCode() {
        return topic.hashCode();
    }

}