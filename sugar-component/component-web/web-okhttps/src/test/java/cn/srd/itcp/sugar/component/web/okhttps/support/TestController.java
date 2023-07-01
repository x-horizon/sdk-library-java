package cn.srd.itcp.sugar.component.web.okhttps.support;

import cn.srd.itcp.sugar.tool.web.WebResponse;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

import static cn.srd.itcp.sugar.tool.web.WebResponse.error;

@RestController
@RequestMapping("/sugar/okHttps")
public class TestController {

    @SneakyThrows
    @PostMapping("/test")
    public WebResponse<Void> test() {
        TimeUnit.SECONDS.sleep(5);
        return error();
    }

}
