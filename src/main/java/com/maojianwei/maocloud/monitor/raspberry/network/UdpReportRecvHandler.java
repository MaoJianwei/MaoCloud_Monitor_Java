package com.maojianwei.maocloud.monitor.raspberry.network;

import com.maojianwei.maocloud.monitor.raspberry.network.api.UdpListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * Created by mao on 2016/9/30.
 */
public class UdpReportRecvHandler extends ChannelInboundHandlerAdapter {

    private List<UdpListener> listeners;

    public UdpReportRecvHandler(List<UdpListener> listeners){
        this.listeners = listeners;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){

        DatagramPacket pkt = (DatagramPacket) msg;
        String str = pkt.content().toString(CharsetUtil.UTF_8);

        for(UdpListener l : listeners){
            l.processMessage(str);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
    }
}
