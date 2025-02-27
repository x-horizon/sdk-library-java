package org.horizon.sdk.library.java.message.engine.server.mqtt.manager.handler;

import org.horizon.sdk.library.java.message.engine.server.mqtt.handler.ClientPublishHandler;
import org.horizon.sdk.library.java.message.engine.server.mqtt.repository.JunctionSignalStepRepository;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wjm
 * @since 2025-01-07 23:22
 */
@Component
public class DevicePublishHandler implements ClientPublishHandler {

    @Autowired JunctionSignalStepRepository junctionSignalStepRepository;

    @SneakyThrows
    @Override
    public Void process(MqttPublishMessage mqttPublishMessage) {
        // String jdbcUrl = "jdbc:TAOS-RS://127.0.0.1:10024?user=root&password=taosdata";
        // try (Connection conn = DriverManager.getConnection(jdbcUrl)) {
        //     System.out.println("Connected to " + jdbcUrl + " successfully.");
        //
        //     // you can use the connection for execute SQL here
        //
        // } catch (Exception ex) {
        //     // please refer to the JDBC specifications for detailed exceptions info
        //     System.out.printf("Failed to connect to %s, %sErrMessage: %s%n",
        //             jdbcUrl,
        //             ex instanceof SQLException ? "ErrCode: " + ((SQLException) ex).getErrorCode() + ", " : "",
        //             ex.getMessage());
        //     // Print stack trace for context in examples. Use logging in production.
        //     ex.printStackTrace();
        //     throw ex;
        // }
        // junctionSignalStepRepository.save(JunctionSignalStepPO.builder().info(1L).build());
        return null;
    }

}