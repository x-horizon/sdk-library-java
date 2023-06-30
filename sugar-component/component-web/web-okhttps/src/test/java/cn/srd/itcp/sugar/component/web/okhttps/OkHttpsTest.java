package cn.srd.itcp.sugar.component.web.okhttps;

import cn.zhxu.okhttps.OkHttps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OkHttpsTest {

    @Test
    public void testOkHttps() {
        String result = OkHttps.sync("http://127.0.0.1:8080/sugar/okhttp/test").post().getBody().toString();
    }

}
