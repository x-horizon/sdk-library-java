// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.web.grpc.server.service;

import cn.srd.library.java.web.grpc.contract.service.FooGrpc;
import cn.srd.library.java.web.grpc.contract.service.FooRequest;
import cn.srd.library.java.web.grpc.contract.service.FooResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author wjm
 * @since 2024-06-13 23:24
 */
@GrpcService
public class FooServerService extends FooGrpc.FooImplBase {

    @Override
    public void sayHello(FooRequest request, StreamObserver<FooResponse> responseObserver) {
        throw new RuntimeException();
        // FooResponse response = FooResponse.newBuilder()
        //         .setMessage("Hello ==> " + request.getName())
        //         .build();
        // responseObserver.onNext(response);
        // responseObserver.onCompleted();
    }

}