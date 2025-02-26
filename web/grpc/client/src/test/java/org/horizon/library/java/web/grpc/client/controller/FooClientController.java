package org.horizon.library.java.web.grpc.client.controller;

import org.horizon.library.java.web.grpc.client.service.FooClientService;
import org.horizon.library.java.web.grpc.contract.service.FooResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wjm
 * @since 2024-06-14 12:02
 */
@RestController
@RequestMapping("/foo")
public class FooClientController {

    @Autowired private FooClientService fooClientService;

    @PostMapping("/sayHello")
    public FooResponse sayHello() {
        return fooClientService.sayHello();
    }

}