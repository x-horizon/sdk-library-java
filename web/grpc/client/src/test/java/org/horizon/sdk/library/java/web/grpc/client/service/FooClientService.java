package org.horizon.sdk.library.java.web.grpc.client.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.horizon.sdk.library.java.web.grpc.contract.service.FooGrpc;
import org.horizon.sdk.library.java.web.grpc.contract.service.FooRequest;
import org.horizon.sdk.library.java.web.grpc.contract.service.FooResponse;
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