package cn.srd.itcp.sugar.component.docker.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Properties For Redis
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
@Data
@Component
@ConfigurationProperties(prefix = "sugar.component.docker")
public class DockerProperties {

    /**
     * docker host，如：tcp://localhost:2376 或 unix:///var/run/docker.sock
     */
    private String dockerHost;

    /**
     * 启用/禁用 TLS 验证（https / http 协议之间切换）
     */
    private Boolean tlsVerifyEnable;

    /**
     * TLS 验证所需证书的路径，只在 {@link #tlsVerifyEnable} 为 true 时生效
     */
    private Integer tlsCertificatePath;

    /**
     * 连接 docker 的最大连接数
     */
    private Integer maxConnectionNumber;

}
