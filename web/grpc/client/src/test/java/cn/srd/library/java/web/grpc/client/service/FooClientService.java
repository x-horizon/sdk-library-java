// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.web.grpc.client.service;

import cn.srd.library.java.web.grpc.contract.service.FooGrpc;
import cn.srd.library.java.web.grpc.contract.service.FooRequest;
import cn.srd.library.java.web.grpc.contract.service.FooResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @author wjm
 * @since 2024-06-13 23:24
 */
@Service
public class FooClientService {

    @GrpcClient("fooClientBlockingStub") private FooGrpc.FooBlockingStub fooBlockingStub;

    public FooResponse sayHello() {
        FooResponse fooResponse = this.fooBlockingStub.sayHello(FooRequest.newBuilder().build());
        // FooResponse fooResponse = this.fooBlockingStub.sayHello(FooRequest.newBuilder().setName("name").build());
        return fooResponse;
    }

}