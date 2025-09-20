package org.horizon.sdk.library.java.tool.rate.limiting.sentinel.controller;

import org.horizon.sdk.library.java.contract.model.protocol.WebResponse;
import org.horizon.sdk.library.java.tool.rate.limiting.sentinel.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.horizon.sdk.library.java.contract.model.protocol.WebResponse.success;

/**
 * @author wjm
 * @since 2025-08-09 00:19
 */
@RestController
@RequestMapping("/")
public class DemoController {

    @Autowired private DemoService demoService;

    @GetMapping(value = "/rate")
    public WebResponse<Void> rate() {
        demoService.rate();
        return success();
    }

}