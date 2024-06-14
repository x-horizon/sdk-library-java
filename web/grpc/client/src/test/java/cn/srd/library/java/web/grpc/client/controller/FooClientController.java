// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.web.grpc.client.controller;

import cn.srd.library.java.web.grpc.client.service.FooClientService;
import cn.srd.library.java.web.grpc.contract.service.FooResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wjm
 * @since 2024-06-14 12:02
 */
@RestController
@RequestMapping("/testGrpc")
public class FooClientController {

    @Autowired private FooClientService fooClientService;

    @PostMapping("/hello")
    public FooResponse sayHello() {
        return fooClientService.sayHello();
    }

}