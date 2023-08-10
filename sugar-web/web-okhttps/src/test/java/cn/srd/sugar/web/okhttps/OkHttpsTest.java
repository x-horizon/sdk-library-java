package cn.srd.sugar.web.okhttps;

import cn.hutool.core.lang.Console;
import cn.srd.sugar.contract.model.core.WebResponse;
import cn.srd.sugar.web.okhttps.core.OkHttpsUtil;
import cn.zhxu.okhttps.HttpResult;
import cn.zhxu.okhttps.OkHttps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OkHttpsTest {

    private static final String URL = "http://127.0.0.1:8080/sugar/okHttps/test";

    @Test
    public void testOkHttps() {
        List<Integer> result = sendAsync(IntStream.rangeClosed(1, 50).mapToObj(ignore -> URL).toList());
        Console.log(result.size());
    }

    public List<Integer> sendAsync(List<String> urls) {
        List<Integer> result = new ArrayList<>();
        urls.stream()
                .map(url -> OkHttps.async(url).post())
                .toList()
                .forEach(httpCall -> {
                    HttpResult httpResult = httpCall.getResult();
                    OkHttpsUtil.requiredHttpStateHealthy(httpResult.getState());
                    httpResult.getBody().toBean(WebResponse.class).notSuccessAndThen(ignore -> result.add(1));
                });
        return result;
    }

}
