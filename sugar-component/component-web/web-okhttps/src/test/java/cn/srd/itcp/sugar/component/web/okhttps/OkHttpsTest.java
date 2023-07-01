package cn.srd.itcp.sugar.component.web.okhttps;

import cn.srd.itcp.sugar.component.web.okhttps.core.OkHttpsUtil;
import cn.srd.itcp.sugar.tool.core.Threads;
import cn.srd.itcp.sugar.tool.web.HttpStatusEnum;
import cn.srd.itcp.sugar.tool.web.WebResponse;
import cn.zhxu.okhttps.HttpCall;
import cn.zhxu.okhttps.HttpResult;
import cn.zhxu.okhttps.OkHttps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OkHttpsTest {

    @Test
    public void testOkHttps() {
        // HttpResult.Body result = OkHttps.sync("http://127.0.0.1:8080/sugar/okHttps/test").post().getBody();

        List<HttpCall> httpCalls = new ArrayList<>();
        for (int index = 1; index < 10; index++) {
            httpCalls.add(
                    OkHttps.async("http://127.0.0.1:8080/sugar/okHttps/test")
                            .setOnResponse((HttpResult httpResult) -> {
                                // 响应回调
                                HttpResult.Body body = httpResult.getBody().cache();
                                HttpStatusEnum.requiredSuccess(httpResult.getStatus(), body.toString(), RuntimeException.class);
                                WebResponse<?> webResponse = body.toBean(WebResponse.class);
                                log.info("{}-{}", Threads.getId(), webResponse);
                            })
                            // .setOnException((IOException exception) -> {
                            //     // 异常回调
                            // })
                            .setOnComplete((HttpResult.State state) -> {
                                // 完成回调，无论成功失败都会执行，并且在 响应|异常回调 之前执行
                                OkHttpsUtil.requiredHttpStateHealthy(state, "your message", RuntimeException.class);
                            })
                            .post()
            );
        }
        // 阻塞等待所有异步请求完成
        httpCalls.forEach(HttpCall::getResult);
    }

}
