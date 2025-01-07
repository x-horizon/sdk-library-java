package cn.srd.library.java.message.engine.server.mqtt.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.ipfilter.AbstractRemoteAddressFilter;

import java.net.InetSocketAddress;

/**
 * @author wjm
 * @since 2025-01-05 19:44
 */
public class IpLimitFlowChannelInboundHandler extends AbstractRemoteAddressFilter<InetSocketAddress> {

    @Override
    protected boolean accept(ChannelHandlerContext channelHandlerContext, InetSocketAddress inetSocketAddress) {
        return true;
    }

}