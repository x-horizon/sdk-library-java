package cn.srd.library.message.bus.kafka;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendTest {

    @Autowired
    private ProducerSample producerSample;

    @SneakyThrows
    @Test
    public void testSend() {
        producerSample.produce();
        TimeUnit.SECONDS.sleep(4L);
    }

}
