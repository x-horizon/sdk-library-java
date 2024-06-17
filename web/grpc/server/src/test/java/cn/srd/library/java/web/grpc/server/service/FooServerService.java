// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.web.grpc.server.service;

import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.web.grpc.contract.service.FooGrpc;
import cn.srd.library.java.web.grpc.contract.service.FooRequest;
import cn.srd.library.java.web.grpc.contract.service.FooResponse;
import io.grpc.Status;
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
        // Assert.of().setMessage("名词不可为空").throwsIfBlank(request.getName());
        if (Nil.isBlank(request.getName())) {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("名词不可为空").asRuntimeException());
        } else {
            FooResponse response = FooResponse.newBuilder()
                    .setMessage("Hello ==> " + request.getName())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

}