package cn.srd.sugar.cloud.natives.docker.support;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;

import java.time.Duration;
import java.util.List;

/**
 * @author wjm
 * @since 2022-08-08 17:59:12
 */
public class Test {

    /**
     * dscs
     *
     * @param args cds
     */
    public static void main(String[] args) {
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://192.168.10.63:2375")
                .withDockerTlsVerify(false)
                .build();

        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();

        DockerClient dockerClient = DockerClientImpl.getInstance(config, httpClient);
        List<Image> images = dockerClient.listImagesCmd().exec();
        dockerClient.pingCmd().exec();
    }

}
