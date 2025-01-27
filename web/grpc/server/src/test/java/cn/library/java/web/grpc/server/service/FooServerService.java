package cn.library.java.web.grpc.server.service;

import cn.library.java.contract.model.throwable.RunningException;
import cn.library.java.tool.lang.functional.Assert;
import cn.library.java.web.grpc.contract.service.FooGrpc;
import cn.library.java.web.grpc.contract.service.FooRequest;
import cn.library.java.web.grpc.contract.service.FooResponse;
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
        Assert.of().setThrowable(RunningException.class).setMessage("dsjcbndjksnds").doThrows();
        FooResponse response = FooResponse.newBuilder()
                .setMessage("Hello ==> " + request.getName())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}