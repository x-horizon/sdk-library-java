// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.web.grpc.client.interceptor;

import io.grpc.*;

/**
 * @author wjm
 * @since 2024-06-14 16:36
 */
public class GrpcClientResponseInterceptor implements ClientInterceptor {

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
        return new ResponseWrapperCaller<>(next.newCall(method, callOptions));
    }

    public static class ResponseWrapperCaller<ReqT, RespT> extends ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT> {

        protected ResponseWrapperCaller(ClientCall<ReqT, RespT> delegate) {
            super(delegate);
        }

        @Override
        public void start(Listener<RespT> responseListener, Metadata headers) {
            super.start(new ResponseWrapperListener<>(responseListener), headers);
        }

    }

    public static class ResponseWrapperListener<RespT> extends ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT> {

        protected ResponseWrapperListener(ClientCall.Listener<RespT> delegate) {
            super(delegate);
        }

        @Override
        public void onMessage(RespT message) {
            super.onMessage(message);
        }

        @Override
        public void onHeaders(Metadata headers) {
            super.onHeaders(headers);
        }

        @Override
        public void onClose(Status status, Metadata trailers) {
            super.onClose(status, trailers);
        }

    }

}