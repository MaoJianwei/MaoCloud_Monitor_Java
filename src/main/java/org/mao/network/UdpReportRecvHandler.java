package org.mao.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import org.mao.network.api.UdpListener;

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
